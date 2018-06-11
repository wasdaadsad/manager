package com.vivo.uhost.protocol.server.utils;

import com.vivo.uhost.message.model.LoginResp;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistencyHash {

    protected static Logger LOG = LoggerFactory.getLogger(ConsistencyHash.class);

    private static ConsistencyHash instance = null;

    private TreeMap<Long, Object> nodes = null;
    // 真实服务器节点信息
    private List<LoginResp> shards = new ArrayList<LoginResp>();
    // 设置虚拟节点数目
    private int VIRTUAL_NUM = 4;

    /**
     * 初始化一致环
     */
    private ConsistencyHash() {
        if (StringUtil.isBlank(ServerConstants.SERVER_ALL_ADDRESS)) {
            return;
        }
        LOG.info("SERVER_HOSTS: {}", ServerConstants.SERVER_ALL_ADDRESS);
        String[] hosts = ServerConstants.SERVER_ALL_ADDRESS.split(Constants.SIGN_COMMA);
        for (String host : hosts) {
            if (StringUtil.isNotBlank(host)) {
                String[] hostPorts = host.split(Constants.SIGN_COLON);
                if (hostPorts.length == 2) {
                    shards.add(new LoginResp(hostPorts[0], Integer.parseInt(hostPorts[1])));
                }
            }
        }

        nodes = new TreeMap<Long, Object>();
        for (int i = 0; i < shards.size(); i++) {
            Object shardInfo = shards.get(i);
            for (int j = 0; j < VIRTUAL_NUM; j++) {
                nodes.put(hash(computeMd5("SHARD-" + i + "-NODE-" + j), j), shardInfo);
            }
        }
    }

    public synchronized static ConsistencyHash getIntance() {
        if (instance == null) {
            instance = new ConsistencyHash();
        }
        return instance;
    }

    /**
     * 根据key的hash值取得服务器节点信息
     *
     * @param hash
     * @return
     */
    public Object getShardInfo(long hash) {
        Long key = hash;
        SortedMap<Long, Object> tailMap = nodes.tailMap(key);
        if (tailMap.isEmpty()) {
            key = nodes.firstKey();
        } else {
            key = tailMap.firstKey();
        }
        return nodes.get(key);
    }

    /**
     * 打印圆环节点数据
     */
    public void printMap() {
        System.out.println(nodes);
    }

    /**
     * 根据2^32把节点分布到圆环上面。
     *
     * @param digest
     * @param nTime
     * @return
     */
    public long hash(byte[] digest, int nTime) {
        long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24) | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
                | ((long) (digest[1 + nTime * 4] & 0xFF) << 8) | (digest[0 + nTime * 4] & 0xFF);

        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }

    /**
     * Get the md5 of the given key. 计算MD5值
     */
    public byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        return md5.digest();
    }

    public LoginResp getHost(String token) {
        LoginResp result = null;
        try {
            byte[] MD5 = computeMd5(token);
            long rv = hash(MD5, 3);
            result = (LoginResp) getShardInfo(rv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ConsistencyHash.getIntance().getHost(UUIDUtils.getUUID()));
    }
}
