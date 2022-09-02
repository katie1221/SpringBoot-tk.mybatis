package com.example.tkmybatisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 注意使用org.mybatis.spring.annotation.MapperScan包下的此注解会连带扫描自定义的MyMapper,会出现
 * java.lang.NoSuchMethodException: tk.mybatis.mapper.provider.base.BaseSelectProvider.异常
 *
 */
@SpringBootApplication
@MapperScan("com.example.tkmybatisdemo.mapper")
public class TkMybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkMybatisDemoApplication.class, args);
    }

    /*
     * 以上的解决方案:
     * 一、使用import tk.mybatis.spring.annotation.MapperScan包下的@MapperScan注解
     * 二、或者不使用@MapperScan注解,改为在dao层的UserDao上添加@Mapper注解来单独标注mapper接口
     * */
}
