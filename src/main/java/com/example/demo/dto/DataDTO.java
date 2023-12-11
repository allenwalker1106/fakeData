package com.example.demo.dto;


public class DataDTO {

    public DataDTO(String group, String name, Object data) {
        this.group = group;
        this.name = name;
        this.data = data;
    }

    private String group;
    private String name;
    private Object data;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
