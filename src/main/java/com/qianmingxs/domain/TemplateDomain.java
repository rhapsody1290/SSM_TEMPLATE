package com.qianmingxs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
//1、表名默认使用类名，驼峰转下划线，如不符合可以使用@Table自己指定
//2、使用通用mapper时字段类型必须为引用类型，基本数据类型必须使用其包裹类型（在使用mapper的Selective方法时，过滤掉null的字段）

@Table(name="tb_user")
public class TemplateDomain implements Serializable{
    @Id
    private Long id;
    // 用户名，字段默认为驼峰形式，如不符合可以使用@Column指定
    @Column(name="user_name")
    private String userName;
    // 密码
    @JsonIgnore
    private String PASS_WORD;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
    // 性别，1男性，2女性
    private Integer sex;
    // 出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    // 创建时间
    private Date created;
    // 更新时间
    private Date updated;

    @Override
    public String toString() {
        return "TemplateDomain{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", PASS_WORD='" + PASS_WORD + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPASS_WORD() {
        return PASS_WORD;
    }

    public void setPASS_WORD(String PASS_WORD) {
        this.PASS_WORD = PASS_WORD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
