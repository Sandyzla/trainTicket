package com.ticket.entity;

import java.sql.Time;

public class Ticket {
    private String orderId;
    private String idNum;
    private String trainId;
    private String staFrom;
    private Time tFrom;
    private String staTo;
    private Time tTo;
    private int carr;
    private int sRow;
    private int sCol;
    private String seatType;
    private int price;

    public Ticket(String orderId, String idNum, String trainId, String staFrom, Time tFrom, String staTo, Time tTo, int carr, int sRow, int sCol, String seatType, int price) {
        this.orderId = orderId;
        this.idNum = idNum;
        this.trainId = trainId;
        this.staFrom = staFrom;
        this.tFrom = tFrom;
        this.staTo = staTo;
        this.tTo = tTo;
        this.carr = carr;
        this.sRow = sRow;
        this.sCol = sCol;
        this.seatType = seatType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
              "orderId='" + orderId + '\'' +
              ", idNum='" + idNum + '\'' +
              ", trainId='" + trainId + '\'' +
              ", staFrom='" + staFrom + '\'' +
              ", tFrom=" + tFrom +
              ", staTo='" + staTo + '\'' +
              ", tTo=" + tTo +
              ", carr=" + carr +
              ", sRow=" + sRow +
              ", sCol=" + sCol +
              ", seatType='" + seatType + '\'' +
              ", price=" + price +
              '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStaFrom() {
        return staFrom;
    }

    public void setStaFrom(String staFrom) {
        this.staFrom = staFrom;
    }

    public Time gettFrom() {
        return tFrom;
    }

    public void settFrom(Time tFrom) {
        this.tFrom = tFrom;
    }

    public String getStaTo() {
        return staTo;
    }

    public void setStaTo(String staTo) {
        this.staTo = staTo;
    }

    public Time gettTo() {
        return tTo;
    }

    public void settTo(Time tTo) {
        this.tTo = tTo;
    }

    public int getCarr() {
        return carr;
    }

    public void setCarr(int carr) {
        this.carr = carr;
    }

    public int getsRow() {
        return sRow;
    }

    public void setsRow(int sRow) {
        this.sRow = sRow;
    }

    public int getsCol() {
        return sCol;
    }

    public void setsCol(int sCol) {
        this.sCol = sCol;
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
