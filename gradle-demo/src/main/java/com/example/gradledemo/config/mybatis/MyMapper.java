package com.example.gradledemo.config.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author qzz
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T>{

}
