package com.ticket.entity;

import java.sql.Time;

public class Order {
   private String orderId;
   private Time createTime;
   private String state;
   private String idNum;
   private int price;

   public Order(String orderId, Time createTime, String state, String idNum, int price) {
      this.orderId = orderId;
      this.createTime = createTime;
      this.state = state;
      this.idNum = idNum;
      this.price = price;
   }

   @Override
   public String toString() {
      return "Order{" +
            "orderId='" + orderId + '\'' +
            ", createTime=" + createTime +
            ", state='" + state + '\'' +
            ", idNum='" + idNum + '\'' +
            ", price=" + price +
            '}';
   }

   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public Time getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Time createTime) {
      this.createTime = createTime;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getIdNum() {
      return idNum;
   }

   public void setIdNum(String idNum) {
      this.idNum = idNum;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }
}
