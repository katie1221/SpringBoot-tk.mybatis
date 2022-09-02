package com.example.tkmybatisdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qzz
 */
public class ResultJson<T>  {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 返回值
     */
    private T data;
    /**
     * 返回值
     */
    private Object extendData;
    @JsonIgnore
    private static final Map EMPTY_MAP = new HashMap<>();

    public ResultJson(){}

    public ResultJson(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code == null ? 0 : code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return (message == null || message.trim().length() < 1) ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultJson success(Object data){
        if(data == null) {
            return success();
        }
        return new ResultJson(0, "操作成功", data);
    }

    public static ResultJson success(){
        return new ResultJson(0, "操作成功", EMPTY_MAP);
    }

    public static ResultJson fail(){
        return new ResultJson(10910, "请求失败", EMPTY_MAP);
    }

    public static ResultJson fail(Integer code, String message){
        return new ResultJson(code, message, EMPTY_MAP);
    }

    public static <T> ResultJson fail(Integer code, String message, T data){
        return new ResultJson(code, message, data != null ? data : EMPTY_MAP);
    }

    public Object getExtendData() {
        return extendData;
    }

    public void setExtendData(Object extendData) {
        this.extendData = extendData;
    }

}
