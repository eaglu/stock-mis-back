package com.qfedu.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

@Table(name = "t_user")
public class User implements Serializable {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String company;
    private Integer userFlag;
    private Integer goodsFlag;
    private Integer galleryFlag;
    private Integer galleryInfoFlag;
    private Integer deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Integer userFlag) {
        this.userFlag = userFlag;
    }

    public Integer getGoodsFlag() {
        return goodsFlag;
    }

    public void setGoodsFlag(Integer goodsFlag) {
        this.goodsFlag = goodsFlag;
    }

    public Integer getGalleryFlag() {
        return galleryFlag;
    }

    public void setGalleryFlag(Integer galleryFlag) {
        this.galleryFlag = galleryFlag;
    }

    public Integer getGalleryInfoFlag() {
        return galleryInfoFlag;
    }

    public void setGalleryInfoFlag(Integer galleryInfoFlag) {
        this.galleryInfoFlag = galleryInfoFlag;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", userFlag=" + userFlag +
                ", goodsFlag=" + goodsFlag +
                ", galleryFlag=" + galleryFlag +
                ", galleryInfoFlag=" + galleryInfoFlag +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
