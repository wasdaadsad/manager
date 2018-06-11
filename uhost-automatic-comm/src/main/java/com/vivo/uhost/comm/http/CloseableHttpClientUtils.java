package com.vivo.uhost.comm.http;

import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.comm.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Title:
 * @Description: 静态类，可关闭的httpClient，用于发送获取号码识别的请求等。
 * @Author:daixing wangwenqian
 * @Since:2016年6月21日
 * @Version:1.0
 */
public class CloseableHttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloseableHttpClientUtils.class);
    private static final int CONNECT_TIMEOUT = VivoConfigManager.getInteger("http.connect.timeout", 5000);
    private static final int SOCKET_TIMEOUT = VivoConfigManager.getInteger("http.so.timeout", 5000);
    private static final int CONNECT_REQUEST_TIMEOUT = VivoConfigManager.getInteger("connect.request.timeout", 5000);
    private static final int DEFAULT_CONNECT_HOST = VivoConfigManager.getInteger("http.default.connect", 30);
    private static final int MAX_TOTAL_CONNECT = VivoConfigManager.getInteger("http.max.connect", 30);
    private static final int RETRY_TIMES = VivoConfigManager.getInteger("http.retry.times", 1);
    private static final String UTF_8 = "UTF-8";

    private static final RequestConfig config;
    private static final ConnectionConfig connectionConfig;
    private static final PoolingHttpClientConnectionManager cm;
    private static final Registry<ConnectionSocketFactory> registry;
    private static final HttpRequestRetryHandler retryHandler;
    private static final CloseableHttpClient closeableHttpClient;

    private static final String EMPTY_STR = "";

    private static final int SECOND_EXCEED_CODE = 429; //请求过多

    private static final int ERROR_NUM_CODE = 412;

    private static volatile AtomicBoolean SECOND_EXCEED = new AtomicBoolean(Boolean.FALSE);


    static {
        LOGGER.debug("连接超时时间,{}", CONNECT_TIMEOUT);
        LOGGER.debug("响应超时时间,{}", SOCKET_TIMEOUT);
        LOGGER.debug("从连接池获取连接的超时时间,{}", CONNECT_REQUEST_TIMEOUT);
        LOGGER.debug("默认连接数,{}", DEFAULT_CONNECT_HOST);
        LOGGER.debug("最大连接数,{}", MAX_TOTAL_CONNECT);
        // 设置缓冲大小 default 8 * 1024
        connectionConfig = ConnectionConfig.custom().setBufferSize(128 * 1024).build();
        // 配置请求的超时设置
        config = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)// 设置从连接池获取连接实例的超时
                .setConnectTimeout(CONNECT_TIMEOUT)// 设置连接超时
                .setSocketTimeout(SOCKET_TIMEOUT)// 设置读取超时
                .build();
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
        } catch (Exception e) {
            throw new RuntimeException("初始化CloseableHttpClientUtils失败，原因是：" + e);
        }

        // https host支持
        registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", new SSLConnectionSocketFactory(sc, new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })).build();
        cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(MAX_TOTAL_CONNECT);// 整个连接池最大连接数
        cm.setDefaultMaxPerRoute(DEFAULT_CONNECT_HOST);// 每路由最大连接数，默认值是2
        // 请求重试处理
        retryHandler = new HttpRetryHandler(RETRY_TIMES);

        ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                HttpHost target = (HttpHost) context.getAttribute(
                        HttpClientContext.HTTP_TARGET_HOST);
                if ("apis.vivo.dianhua.cn".equalsIgnoreCase(target.getHostName())) {
                    // Keep alive for 20 seconds only
                    return 20 * 1000;
                } else {
                    return -1;
                }
            }
        };
        closeableHttpClient = HttpClients.custom().setDefaultConnectionConfig(connectionConfig)
                .setRetryHandler(retryHandler).setKeepAliveStrategy(keepAliveStrategy).setConnectionManager(cm).build();
    }


    /**
     * 发送http get请求
     *
     * @param url NotNull 请求url地址
     * @return NotNull 响应结果
     */
    public static String get(String url) {
        return get(url, null, null);
    }


    /**
     * 发送http get请求
     *
     * @param url       NotNull 请求url地址
     * @param paramters Nullable 请求参数
     * @return NotNull 响应结果
     */
    public static String get(String url, Map<String, String> paramters) {
        return get(url, null, paramters);
    }

    /**
     * 发送http get请求
     *
     * @param url       NotNull 请求url地址
     * @param headers   Nullable 请求头
     * @param paramters Nullable 请求参数
     * @return NotNull 响应结果
     */
    public static String get(String url, Map<String, String> headers, Map<String, String> paramters) {
        HttpGet httpGet = buildHttpGet(url, headers, paramters);
        if (paramters != null) {
            return sendRequest(httpGet, JsonUtil.bean2Json(paramters));
        } else {
            return sendRequest(httpGet, null);
        }
    }


    /**
     * 构建一个http Get请求
     *
     * @param url       NotNull 请求url地址
     * @param headers   Nullable 请求头
     * @param paramters Nullable 请求参数
     * @return NotNull HttpGet对象
     */
    private static HttpGet buildHttpGet(String url, Map<String, String> headers, Map<String, String> paramters) {
        HttpGet httpGet;
        if (null != paramters && !paramters.isEmpty()) {
            URIBuilder ub = new URIBuilder();
            ub.setPath(url);
            ArrayList<NameValuePair> pairs = covertParams2NVPS(paramters);
            ub.setParameters(pairs);

            httpGet = new HttpGet(getURI(ub));

            httpGet.setConfig(config);
        } else {
            httpGet = new HttpGet(url);
        }

        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> param : headers.entrySet()) {
                httpGet.addHeader(param.getKey(), param.getValue());
            }
        }
        httpGet.setConfig(config);
        return httpGet;
    }


    /**
     * @param params 参数集合
     * @return nameValuePair集合，用于构建uri参数
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return pairs;
    }

    /**
     * 通过URIBuilder构建uri
     *
     * @param ub uri builder对象
     * @return 构建的uri对象
     */
    private static URI getURI(URIBuilder ub) {
        try {
            return ub.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String post(String url, String requestBody) throws HttpInvokeException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        httpPost.setEntity(new StringEntity(requestBody, UTF_8));
        httpPost.setHeader("Content-Type", "application/json");
        return sendRequest(httpPost, requestBody);
    }


    /**
     * @param request       NotNull 包含请求所有信息的对象
     * @param paramtersJson Nullable 参数，用于记录日志使用
     * @return NotNull 响应的内容
     */
    private static String sendRequest(HttpRequestBase request, String paramtersJson) {
        CloseableHttpResponse response = null;
        if (SECOND_EXCEED.get()) {//发现code 429,则当前秒内剩下的时间均返回空;防止秒内请求超过电话邦限制
            LOGGER.warn("request second exceed");
            return EMPTY_STR;
        }
        PoolStats status = cm.getTotalStats();
        LOGGER.debug("send http request start: pram:{}|avi:{}|les:{}", paramtersJson, status.getAvailable(), status.getLeased());
        long time = System.currentTimeMillis();
        try {
            response = closeableHttpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("调用http请求失败: {}, 耗时：{} ms, 响应码: {}", request.getURI(), (System.currentTimeMillis() - time), statusCode);
                if (statusCode == SECOND_EXCEED_CODE) {
                    if (SECOND_EXCEED.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
                        try {
                            Thread.sleep(System.currentTimeMillis() % 1000);
                        } catch (InterruptedException ingnore) {
                            //doNothing
                        }
                        SECOND_EXCEED.set(Boolean.FALSE);
                    }
                } else if (statusCode == ERROR_NUM_CODE) {
                    if (!StringUtils.isBlank(paramtersJson)) {
                        LOGGER.error("telNum error:{}", paramtersJson);
                    }
                } else {
                    throw new HttpInvokeException(statusCode, "调用http服务返回响应错误, url: " + request.getURI() + ",响应码：" + statusCode);
                }
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                LOGGER.debug("调用http请求成功:{}, 耗时：{} ms, 响应内容: {}",
                        request.getURI(), (System.currentTimeMillis() - time), result
                );
                return result;
            } else {
                LOGGER.debug("调用http请求成功:{}, 耗时：{} ms, 响应内容: {}",
                        request.getURI(), (System.currentTimeMillis() - time), "");
            }
        } catch (Throwable e) {
            LOGGER.error("调用http请求失败: {}, 耗时：{} ms", request.getURI(), (System.currentTimeMillis() - time), e);
            if (!StringUtils.isBlank(paramtersJson)) {
                LOGGER.info("send http request error: pram:{}|avi:{}|les:{}|cause:{}", paramtersJson, status.getAvailable(), status.getLeased(), e.getCause());
            }
        } finally {
            request.releaseConnection();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ignore) {
                }
            }
        }
        return EMPTY_STR;
    }

}
