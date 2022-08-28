package com.company.project.vo;
//键值对实体类
public class LockVo {
    private String name;
    private String value;

    public LockVo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
