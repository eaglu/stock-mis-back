package com.qfedu.entity;

import com.qfedu.base.TimeLimit;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_bill")
public class Bill {
    @Id
    private Integer id;
    @Column(name = "bill_id")
    private String billId;
    private String username;
    @Column(name = "user_company")
    private String userCompany;

    @Column(name = "gallery_name")
    private String galleryName;

    @Column(name = "gallery_id")
    private Integer galleryId;

    @Column(name = "operator_id")
    private Integer operatorId;

    @Column(name = "deleted")
    private Integer deleted;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public Integer getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Integer galleryId) {
        this.galleryId = galleryId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billId='" + billId + '\'' +
                ", username='" + username + '\'' +
                ", userCompany='" + userCompany + '\'' +
                ", galleryName='" + galleryName + '\'' +
                ", date=" + date +
                '}';
    }
}
