package com.qfedu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/****
 * id，仓库名称：name，仓库位置：location，操作人:operatorId，操作人:operatorName，操作时间:operatorTime
 */
@Table(name = "t_repo")
public class Repo {
    @Id
    private Integer id;
    private String name;
    private String location;
    @Column(name = "operator_id")
    private Integer operatorId;
    @Column(name = "operator_name")
    private String operatorName;
    @Column(name = "operator_time")
    private Date operatorTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
}
