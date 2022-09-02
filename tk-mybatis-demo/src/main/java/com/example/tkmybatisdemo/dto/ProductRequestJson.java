package com.example.tkmybatisdemo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author qzz
 */
@Data
public class ProductRequestJson {

    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 商品售价
     */
    private Double salePrice;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Integer createBy;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改者
     */
    private Integer updateBy;
}
