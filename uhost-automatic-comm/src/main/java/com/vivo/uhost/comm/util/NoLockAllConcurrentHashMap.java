package com.vivo.uhost.comm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-30
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class NoLockAllConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(NoLockAllConcurrentHashMap.class);
    private final AtomicInteger size = new AtomicInteger();

    @Override
    public V put(K key, V value){
        V oldValue = super.put(key, value);
        if(oldValue == null){
            size.incrementAndGet();
        }
        return oldValue;
    }

    @Override
    public V putIfAbsent(K key, V value){
        V oldValue = super.putIfAbsent(key, value);
        if(oldValue == null){
            size.incrementAndGet();
        }
        return oldValue;
    }

    @Override
    public int size(){
        int superSize = superSize();
        int mySize = size.get();
        if(superSize != mySize){
            logger.debug("size error! super: {}, size: {}", superSize, mySize);
        }
//        return size.get();
        return mySize;
    }

    public int superSize(){
        return super.size();
    }

    @Override
    public V remove(Object key) {
        V oldValue = super.remove(key);
        if(oldValue != null){
            size.decrementAndGet();
        }
        return oldValue;
    }

    @Override
    public boolean remove(Object key, Object value) {
        boolean result = super.remove(key, value);
        if(result){
            size.decrementAndGet();
        }
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        size.set(0);
    }

    public static void main(String[] args) {
        testSuper();
        test();
    }

    public static void test(){
        final NoLockAllConcurrentHashMap<Integer, String> map = new NoLockAllConcurrentHashMap<Integer, String>();
        final CountDownLatch latch = new CountDownLatch(1000);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 10000; j++){
                        map.put(finalI * 10000 + j, "");
                        map.size();
                    }
                    latch.countDown();
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("map size: " + map.size());
        System.out.println("size: " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void testSuper(){
        final NoLockAllConcurrentHashMap<Integer, String> map = new NoLockAllConcurrentHashMap<Integer, String>();
        final CountDownLatch latch= new CountDownLatch(1000);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 10000; j++){
                        map.put(finalI * 10000 + j, "");
                        map.superSize();
                    }
                    latch.countDown();
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("map super size: " + map.size());
        System.out.println("super size: " + (System.currentTimeMillis() - start) + "ms");
    }
}
