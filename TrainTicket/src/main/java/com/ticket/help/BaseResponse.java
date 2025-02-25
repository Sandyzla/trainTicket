package com.ticket.help;

public class BaseResponse {
    private String code;
    private String message;
    public BaseResponse(){}
    public BaseResponse(String code) {
        this.code = code;
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
