package com.together.mybatis;

import org.apache.ibatis.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * use as mybatis second level cache
 * @author jiangjian
 */
public class MyCacheImpl implements Cache {
    Map<Object, Object> stores = new HashMap<>();

    public MyCacheImpl(String namespace) {
        System.out.println(">>>>>>current namespace:" + namespace);

    }
    @Override
    public String getId() {
        return "myCacheImpl";
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println(">>>>cache key: " + key);
        System.out.println(">>>>cache value: " + value);
        stores.put(key, value);
        System.out.println(">>>current size: " + stores.size());
    }

    @Override
    public Object getObject(Object key) {
        Object value =  stores.get(key);
        System.out.println(">>>> value from cache is :" + value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println(">>>>>remove cache key:" + key);
        return stores.remove(key);
    }

    @Override
    public void clear() {
        System.out.println(">>>>cache clear");
        stores = new HashMap<>(1);
    }

    @Override
    public int getSize() {
        return stores.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return new ReentrantReadWriteLock();
    }
}
