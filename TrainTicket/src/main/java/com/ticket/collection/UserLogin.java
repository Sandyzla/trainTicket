package com.ticket.collection;
//仅用于接收 前端登录请求数据
public class UserLogin {
    private String id; //包含身份证或手机号
    private String ps;

    @Override
    public String toString() {
        return "UserLogin{" +
                "id='" + id + '\'' +
                ", ps='" + ps + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

}
