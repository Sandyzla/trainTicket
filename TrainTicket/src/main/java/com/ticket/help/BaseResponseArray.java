package com.ticket.help;

import java.util.List;
//返回一个数组类型
public class BaseResponseArray<T> {

    private String code;
    private String message;
    private List<T> data;
    public BaseResponseArray(){}

    public BaseResponseArray(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseResponseArray(String code, List<T> data) {
        this.code = code;
        this.data = data;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
