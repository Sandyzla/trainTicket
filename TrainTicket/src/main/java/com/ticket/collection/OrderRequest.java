package com.ticket.collection;

import java.util.List;

public class OrderRequest {
   private String requestIdNum;
   private String trainId;
   private int noFrom;
   private int noTo;
   private List<List<OrderForm>> lists;

   public OrderRequest(String requestIdNum, String trainId, int noFrom, int noTo, List<List<OrderForm>> lists) {
      this.requestIdNum = requestIdNum;
      this.trainId = trainId;
      this.noFrom = noFrom;
      this.noTo = noTo;
      this.lists = lists;
   }

   @Override
   public String toString() {
      return "OrderRequest{" +
            "requestIdNum='" + requestIdNum + '\'' +
            ", trainId='" + trainId + '\'' +
            ", noFrom=" + noFrom +
            ", noTo=" + noTo +
            ", lists=" + lists.toString() +
            '}';
   }

   public String getRequestIdNum() {
      return requestIdNum;
   }

   public void setRequestIdNum(String requestIdNum) {
      this.requestIdNum = requestIdNum;
   }

   public String getTrainId() {
      return trainId;
   }

   public void setTrainId(String trainId) {
      this.trainId = trainId;
   }

   public int getNoFrom() {
      return noFrom;
   }

   public void setNoFrom(int noFrom) {
      this.noFrom = noFrom;
   }

   public int getNoTo() {
      return noTo;
   }

   public void setNoTo(int noTo) {
      this.noTo = noTo;
   }

   public List<List<OrderForm>> getLists() {
      return lists;
   }

   public void setLists(List<List<OrderForm>> lists) {
      this.lists = lists;
   }
}
