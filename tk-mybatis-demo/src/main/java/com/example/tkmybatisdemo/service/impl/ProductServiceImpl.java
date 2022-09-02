package com.example.tkmybatisdemo.service.impl;

import com.example.tkmybatisdemo.dto.ProductRequestJson;
import com.example.tkmybatisdemo.dto.ResultJson;
import com.example.tkmybatisdemo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qzz
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 添加商品信息
     * @param productRequestJson
     * @return
     */
    @Override
    public ResultJson addProduct(ProductRequestJson productRequestJson) {
        productRequestJson.setCreateTime(new Date());
        productRequestJson.setCreateBy(1);
        return null;
    }
}
