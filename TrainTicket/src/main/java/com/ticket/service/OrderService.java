package com.ticket.service;

import com.ticket.collection.OrderRequest;
import com.ticket.collection.OrderForm;
import com.ticket.collection.OrderView;
import com.ticket.entity.Seat;
import com.ticket.entity.Ticket;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseArray;
import com.ticket.help.OrderSubmittedResponse;

import java.sql.Time;
import java.util.List;

public interface OrderService {
   public void checkOrderForm(OrderRequest request);
   public void markSeatOccupiedAndAddTicket(OrderRequest request);
   public BaseResponseArray<OrderView>  getOrderBasicInf(String idNum, String state);
}
