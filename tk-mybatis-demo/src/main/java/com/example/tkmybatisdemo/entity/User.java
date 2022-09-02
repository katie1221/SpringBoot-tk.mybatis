package com.example.tkmybatisdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体
 *
 * 其中@Table即数据表表名，@Column即列名，@Id作为主键，需要注意，@Id注解不可有多个，@Transient即冗余字段，不与数据库任何字段对应。
 * 注意多数据源的情况：则@Table注解中可以写成“{数据库名}.{架构名}.{表名}”，如：@Table(name=“db.dbo.tableName”)
 *
 * GenerationType的参数说明：
 *    TABLE：使用一个特定的数据库表格来保存主键。(用的比较少)
 *    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。(mysql不支持，其实和auto一样)
 *    IDENTITY：主键由数据库自动生成（主要是自动增长型，这个用的比较多）
 *    AUTO：主键由程序控制。(不是自增)
 * @author qzz
 */
@Data
@Table(name="t_user")
public class User {

    /**
     * IDENTITY：主键由数据库自动生成（主要是自动增长型，这个用的比较多）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String remarks;

    @Column
    private String phone;

    /**
     * 性别 0 男 1 女
     */
    @Column
    private Integer sex;

    /**
     * 状态 0启用 1禁用
     */
    @Column
    private Integer status;

    /**
     * @JsonFormat：由后端传递给前端
     * @DateTimeFormat:由前端传递给后端
     */
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column
    private String createUser;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column
    private String updateUser;



}
