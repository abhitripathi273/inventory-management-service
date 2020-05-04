package com.microservices.inventorymanagementservice.exceptions;

@FunctionalInterface
public interface MyFunction<T, R> {
    R apply(T t) throws OutOfStockException;
}
