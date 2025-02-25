package com.ticket.help;

public class BaseResponseData<T> {

    private String code;
    private String message;
    private T data;
    public BaseResponseData(){}
    public BaseResponseData(String code) {
        this.code = code;
    }

    public BaseResponseData(String res_code, String message) {
        this.code = res_code;
        this.message = message;
    }

    public BaseResponseData(String res_code, T data) {
        this.code = res_code;
        this.data = data;
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
