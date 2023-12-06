package com.example.demo.controller;

import com.example.demo.service.FakeDataService;
import com.google.gson.Gson;
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


    public static String asString(Resource resource) throws Exception{
        Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        return FileCopyUtils.copyToString(reader);

    }
    public static String readFileToString(String path) throws Exception{
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        return asString(resource);
    }

    public static String buildPath(String type, String id){
        if(Objects.nonNull(type) && Objects.nonNull(id)){
            return "classpath:data/"+type+"/"+id;
        }
        return null;
    }

    @GetMapping("/data/{group}/{id}")
    public Object getFakeData(@PathVariable("group") String group, @PathVariable("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String resourcePath = buildPath(group,id);
            if(Objects.nonNull(resourcePath)){
                try{
                    String content = readFileToString(resourcePath);
                    return gson.fromJson(content,Object.class);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @GetMapping("/data")
    public Object getSampleData(@RequestParam("group") String group, @RequestParam("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String resourcePath = buildPath(group,id);
            if(Objects.nonNull(resourcePath)){
                try{
                    String content = readFileToString(resourcePath);
                    return gson.fromJson(content,Object.class);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @PostMapping("/data/{group}/{id}")
    public Object postFakeData(@PathVariable("group") String group, @PathVariable("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String resourcePath = buildPath(group,id);
            if(Objects.nonNull(resourcePath)){
                try{
                    String content = readFileToString(resourcePath);
                    return gson.fromJson(content,Object.class);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @PostMapping("/data")
    public Object postSampleData(@RequestParam("group") String group, @RequestParam("id") String id){
        Gson gson = new Gson();
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            String resourcePath = buildPath(group,id);
            if(Objects.nonNull(resourcePath)){
                try{
                    String content = readFileToString(resourcePath);
                    return gson.fromJson(content,Object.class);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return gson.fromJson("{}",Object.class);
    }

    @PostMapping("/add")
    public Object createSampleData(@RequestParam String group, @RequestParam String name, @RequestBody Object data){
        if(Objects.nonNull(group) && Objects.nonNull(name)){
            if(!this.groupExists(group)){
                if(!group.isBlank()){
                    fakeDataService.createDir(group);
                }else {
                    return "";
                }
            }
            String filePath = "classpath:data/"+group+"/"+name;
            String content = "";
            if(Objects.nonNull(data)){
                content = data.toString();
            }
            this.writeContent(filePath,content);
        }
        return data;
    }

    private void writeContent(String filePath, String Content){
        try {
            String rootPath = new ClassPathResource(".").getFile().getPath()+"/";
            String convertPath = filePath.replace("classpath:",rootPath);
            File targetFile = new File(convertPath);
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter(targetFile);
            myWriter.write(Content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private boolean groupExists(String group) {
        boolean exists = false;
        if(Objects.nonNull(group) && !group.isBlank()){
            String path = "classpath:data";
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(path);
            try{
                File dataDir = resource.getFile();
                String[] dirContent = dataDir.list();
                if(Objects.nonNull(dirContent)){
                    List<String> folderContent =  Arrays.asList(dirContent);
                    exists = folderContent.stream().anyMatch(file -> file.equals(group));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return exists;
    }
}
