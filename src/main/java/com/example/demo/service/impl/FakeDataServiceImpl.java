package com.example.demo.service.impl;

import com.example.demo.service.FakeDataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FakeDataServiceImpl implements FakeDataService {
    Map<String, Map<String, String>> dataStore;

    public FakeDataServiceImpl(){
        this.dataStore = new HashMap<>();
    }


    @Override
    public String getData(String group, String name) {
        String data =null;
        if(Objects.isNull(this.dataStore)){
            this.dataStore = new HashMap<>();
        }
        if(dataStore.containsKey(group)){
            Map<String, String> groupMap = dataStore.get(group);
            if(Objects.nonNull(groupMap)){
                data = groupMap.get(name);
            }
        }
        return data;
    }

    @Override
    public void addData(String group, String name, Object data) {
        if(Objects.nonNull(group) && !group.isBlank() && Objects.nonNull(name) && !name.isBlank() && Objects.nonNull(data)){
            if(Objects.isNull(this.dataStore)){
                this.dataStore = new HashMap<>();
            }
            if(!this.dataStore.containsKey(group)){
                this.dataStore.put(group, new HashMap<>());
            }
            String content = data.toString();
            this.dataStore.get(group).put(name,content);
        }

    }
}
