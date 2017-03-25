package com.wang.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by WANGDD on 2017/3/25.
 */
public class Student {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空！")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
