package com.example.demo.service.db;

import java.util.List;

public interface DbDataSaver<T> {
    void saveData(List<T> data);
}
