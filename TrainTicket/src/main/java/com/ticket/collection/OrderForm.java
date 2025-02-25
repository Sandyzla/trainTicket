package com.ticket.collection;
//接收前端的 购票请求
public class OrderForm {
    private String idType;
    private String idNum;
    private String userName;
    private String seatType;
    private int price;

    public OrderForm(String idType, String idNum, String userName, String seatType, int price) {
        this.idType = idType;
        this.idNum = idNum;
        this.userName = userName;
        this.seatType = seatType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
              "idType='" + idType + '\'' +
              ", idNum='" + idNum + '\'' +
              ", userName='" + userName + '\'' +
              ", seatType='" + seatType + '\'' +
              ", price=" + price +
              '}';
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
