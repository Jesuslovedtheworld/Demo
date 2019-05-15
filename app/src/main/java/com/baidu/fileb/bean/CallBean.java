package com.baidu.fileb.bean;

import java.io.Serializable;

public class CallBean implements Serializable {
    private String image;
    private String title;
    private String name;
    private String desc;
    private String time;

    public CallBean(String image, String title, String name, String desc, String time) {
        this.image = image;
        this.title = title;
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CallBean(String image, String title, String name, String desc) {

        this.image = image;
        this.title = title;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CallBean{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
