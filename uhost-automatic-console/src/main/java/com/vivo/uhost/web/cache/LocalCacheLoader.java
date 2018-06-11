/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.web.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.service.impl.DeviceServiceImpl;
import com.vivo.uhost.service.impl.ModelInfoServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author huangxiaoqun  2017/11/15 18:50
 * @version 1.0
 * @description 本地缓存器
 */
@Component
public class LocalCacheLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalCacheLoader.class);

    //机型信息缓存
    private LoadingCache<String, List<String>> modleCache;

    //uhost缓存
    private LoadingCache<String, List<String>> uHostCache;

    private LoadingCache<String, List<String>> workShop;

    @Autowired
    private ModelInfoServiceImpl modelInfoServiceImpl;

    @Autowired
    private DeviceServiceImpl deviceServiceImpl;

    @PostConstruct
    public void init() {
        final List<DeviceInfo> res = deviceServiceImpl.findAll();
        modleCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(CfgContants.CACHE_EXPIRE_COMMON, TimeUnit.HOURS)
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String key) throws Exception {
                        List<String> data = new ArrayList<>();
                        Set<String> modleSet = new HashSet<>();
                        List<ModelInfoBO> models = modelInfoServiceImpl.findAll();
                        if (CollectionUtils.isEmpty(models)) {
                            return null;
                        }
                        for (ModelInfoBO model : models) {
                            modleSet.add(model.getModelVersion());
                        }
                        data.addAll(modleSet);
                        return data;

                    }
                });

        uHostCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(CfgContants.CACHE_EXPIRE_COMMON, TimeUnit.HOURS)
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String key) throws Exception {
                        //List<Device> res = deviceService.findAll();
                        List<String> data = new ArrayList<>();
                        Set<String> set = new HashSet<>();
                        if (CollectionUtils.isEmpty(res)) {
                            return null;
                        }
                        for (DeviceInfo re : res) {
                            set.add(re.getUhost());
                        }
                        data.addAll(set);
                        return data;
                    }
                });
        /*workShop = CacheBuilder.newBuilder()
                .refreshAfterWrite(CfgContants.CACHE_EXPIRE_COMMON, TimeUnit.HOURS)
                .build(new CacheLoader() {
                    @Override
                    public Object load(Object o) throws Exception {
                    return null;
            }
        });*/

    }





}
