package com.example.demo.service;

import java.util.List;

public interface FakeDataService {
    Object getData(String group, String name);

    void addData(String group, String name, Object data);
}
