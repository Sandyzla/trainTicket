package com.ticket.collection;

public class SeatNumAndPrice {
   private int num;
   private double price;

   public SeatNumAndPrice(int num, double price) {
      this.num = num;
      this.price = price;
   }

   public int getNum() {
      return num;
   }

   public void setNum(int num) {
      this.num = num;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }
}
