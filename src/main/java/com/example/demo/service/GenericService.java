package com.example.demo.service;

import java.util.List;

public interface GenericService<T> {
    T add(T item);

    void addAll(List<T> items);
}
