package com.example.tkmybatisdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {

    /**
     * IDENTITY：主键由数据库自动生成（主要是自动增长型，这个用的比较多）
     */
    private Integer id;

    private String name;

    private Integer age;

    private String remarks;

    private String phone;

    /**
     * 性别 0 男 1 女
     */
    private Integer sex;

    /**
     * 状态 0启用 1禁用
     */
    private Integer status;

    /**
     * @JsonFormat：由后端传递给前端
     * @DateTimeFormat:由前端传递给后端
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
}
