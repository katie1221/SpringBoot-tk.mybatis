package com.example.tkmybatisdemo.service;

import com.example.tkmybatisdemo.dto.ProductRequestJson;
import com.example.tkmybatisdemo.dto.ResultJson;

/**
 * @author qzz
 */
public interface ProductService {

    /**
     * 添加商品信息
     * @param productRequestJson
     * @return
     */
    ResultJson addProduct(ProductRequestJson productRequestJson);
}
