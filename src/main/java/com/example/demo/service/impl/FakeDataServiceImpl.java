package com.example.demo.service.impl;

import com.example.demo.service.FakeDataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FakeDataServiceImpl implements FakeDataService {

    @Override
    public void createDir(String group) {
        boolean success = false;
        if(Objects.nonNull(group)){
            String path = this.getDataPath()+group;
            File groupDir = new File(path);
            if (!groupDir.exists()) {
                success = groupDir.mkdirs();
            }
        }
    }

    @Override
    public String getDataPath() {
        return getClass().getClassLoader().getResource(".").getPath() + "fake/data/";
    }
}
