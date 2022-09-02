package com.example.tkmybatisdemo.controller;

import com.example.tkmybatisdemo.dto.ProductRequestJson;
import com.example.tkmybatisdemo.dto.ResultJson;
import com.example.tkmybatisdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 * @author qzz
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 添加商品信息
     * @param productRequestJson
     * @return
     */
    @PostMapping("/addProduct")
    public ResultJson addProduct(@RequestBody ProductRequestJson productRequestJson){
       return productService.addProduct(productRequestJson);
    }
}
