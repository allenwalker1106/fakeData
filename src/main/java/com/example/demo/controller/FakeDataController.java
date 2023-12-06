package com.example.demo.controller;

import com.example.demo.service.FakeDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fake")
public class FakeDataController {

    @Autowired
    private FakeDataService fakeDataService;

    @GetMapping("/data/{group}/{id}")
    public Object getFakeData(@PathVariable("group") String group, @PathVariable("id") String id){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String content = fakeDataService.getData(group,id);
            if(Objects.nonNull(content)){
                try{
                    JsonReader reader = new JsonReader(new StringReader(content));
                    reader.setLenient(true);
                    return gson.fromJson(reader,Object.class);
                }catch(Exception e){
                    return  content;
                }
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @GetMapping("/data")
    public Object getSampleData(@RequestParam("group") String group, @RequestParam("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String content = fakeDataService.getData(group,id);
            if(Objects.nonNull(content)){
                return gson.fromJson(content,Object.class);
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @PostMapping("/data/{group}/{id}")
    public Object postFakeData(@PathVariable("group") String group, @PathVariable("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String content = fakeDataService.getData(group,id);
            if(Objects.nonNull(content)){
                return gson.fromJson(content,Object.class);
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @PostMapping("/data")
    public Object postSampleData(@RequestParam("group") String group, @RequestParam("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String content = fakeDataService.getData(group,id);
            if(Objects.nonNull(content)){
                return gson.fromJson(content,Object.class);
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @PostMapping("/add")
    public Object createSampleData(@RequestParam String group, @RequestParam String name, @RequestBody Object data){
        Object content = "Create Data Fail";
        if(Objects.nonNull(group) && Objects.nonNull(name)){
            fakeDataService.addData(group,name,data);
            content = data;
        }
        return content;
    }
}
