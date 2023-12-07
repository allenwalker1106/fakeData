package com.example.demo.service.impl;

import com.example.demo.service.FakeDataService;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FakeDataServiceImpl implements FakeDataService {
    static Map<String, Map<String, Object>> dataStore;

    public FakeDataServiceImpl(){
        if(Objects.isNull(FakeDataServiceImpl.dataStore)) {
            FakeDataServiceImpl.dataStore = new ConcurrentHashMap<>();
        }
    }


    @Override
    public Object getData(String group, String name) {
        Object data =null;
        if(Objects.isNull(FakeDataServiceImpl.dataStore)){
            FakeDataServiceImpl.dataStore = new ConcurrentHashMap<>();
        }
        if(FakeDataServiceImpl.dataStore.containsKey(group)){
            Map<String, Object> groupMap = FakeDataServiceImpl.dataStore.get(group);
            if(Objects.nonNull(groupMap)){
                data = groupMap.get(name);
            }
        }
        return data;
    }

    @Override
    public void addData(String group, String name, Object data) {
        Gson gson = new Gson();
        if(Objects.nonNull(group) && !group.isBlank() && Objects.nonNull(name) && !name.isBlank() && Objects.nonNull(data)){
            if(Objects.isNull(FakeDataServiceImpl.dataStore)){
                FakeDataServiceImpl.dataStore = new HashMap<>();
            }
            if(!FakeDataServiceImpl.dataStore.containsKey(group)){
                FakeDataServiceImpl.dataStore.put(group, new HashMap<>());
            }
            FakeDataServiceImpl.dataStore.get(group).put(name,data);
        }

    }
}
