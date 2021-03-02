package com.nrkt.springbootminio.util;

public interface LinkUtils<T> {

    T addOperationWithLink(T clazz);

    T getOperationWithLink(T clazz);
}
