package com.example.demo.service.impl;

import com.example.demo.service.FakeDataService;
import com.example.demo.service.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FireBaseService firebaseService;

    public FakeDataServiceImpl(){
    }


    @Override
    public Object getData(String group, String name) {
        Object data =null;
        if(Objects.nonNull(group) && Objects.nonNull(name)){
            data = firebaseService.fetchData(group,name);
        }
        return data;
    }

    @Override
    public void addData(String group, String name, Object data) {
        if(Objects.nonNull(group) && !group.isBlank() && Objects.nonNull(name) && !name.isBlank() && Objects.nonNull(data)){
            firebaseService.storeData(group,name,data);
        }

    }
}
