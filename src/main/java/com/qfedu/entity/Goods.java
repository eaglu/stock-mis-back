package com.qfedu.entity;

import javax.persistence.*;

@Table(name = "goods")
public class Goods {
    @Id
    private int id;
    private String name;
    private String code;
    private String color;
    private String size;
    private int amount;
    private int deleteFlag;
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


}
