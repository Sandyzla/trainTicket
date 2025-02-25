package com.ticket.collection;

import java.sql.Time;

public class TicketView
{
   private String orderId;
   private String passengerName;
   private String trainId;
   private String staFrom;
   private Time tFrom;
   private String staTo;
   private Time tTo;
   private String duration;
   private int carr;
   private int sRow;
   private String sCol;
   private String seatType;
   private int price;

   public TicketView() {
   }

   public TicketView(String orderId, String passengerName, String trainId, String staFrom, Time tFrom, String staTo, Time tTo, String duration, int carr, int sRow, String sCol, String seatType, int price) {
      this.orderId = orderId;
      this.passengerName = passengerName;
      this.trainId = trainId;
      this.staFrom = staFrom;
      this.tFrom = tFrom;
      this.staTo = staTo;
      this.tTo = tTo;
      this.duration = duration;
      this.carr = carr;
      this.sRow = sRow;
      this.sCol = sCol;
      this.seatType = seatType;
      this.price = price;
   }

   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getPassengerName() {
      return passengerName;
   }

   public void setPassengerName(String passengerName) {
      this.passengerName = passengerName;
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

   public String getsCol() {
      return sCol;
   }

   public void setsCol(String sCol) {
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
