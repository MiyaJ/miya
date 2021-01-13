package com.miya.system.common;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
