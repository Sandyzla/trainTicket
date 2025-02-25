package com.ticket.collection;

import java.sql.Time;

public class OrderView {
   private String orderId;
   private String userName;
   private Time createTime;
   private String state;
   private int price;
   private String trainId;
   private String staFrom;
   private Time tFrom;
   private String staTo;
   private Time tTo;
   private String duration;

   public OrderView() {
   }

   public OrderView(String orderId, String userName, Time createTime, String state, int price, String trainId, String staFrom, Time tFrom, String staTo, Time tTo, String duration) {
      this.orderId = orderId;
      this.userName = userName;
      this.createTime = createTime;
      this.state = state;
      this.price = price;
      this.trainId = trainId;
      this.staFrom = staFrom;
      this.tFrom = tFrom;
      this.staTo = staTo;
      this.tTo = tTo;
      this.duration = duration;
   }

   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
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

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
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

   public String getDuration() {
      return duration;
   }

   public void setDuration(String duration) {
      this.duration = duration;
   }
}
