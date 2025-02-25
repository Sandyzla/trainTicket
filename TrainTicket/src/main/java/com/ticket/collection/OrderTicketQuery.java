package com.ticket.collection;

public class OrderTicketQuery {
   private String idNum;
   private String state;
   private int index;

   public OrderTicketQuery(String idNum, String state, int index) {
      this.idNum = idNum;
      this.state = state;
      this.index = index;
   }

   @Override
   public String toString() {
      return "OrderTicketQuery{" +
            "idNum='" + idNum + '\'' +
            ", state='" + state + '\'' +
            ", index=" + index +
            '}';
   }

   public String getIdNum() {
      return idNum;
   }

   public void setIdNum(String idNum) {
      this.idNum = idNum;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public int getIndex() {
      return index;
   }

   public void setIndex(int index) {
      this.index = index;
   }
}
