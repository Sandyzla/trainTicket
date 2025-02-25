package com.ticket.service;

import java.sql.Time;
import java.util.Map;

public interface PaymentService {
   Map<String, Integer> calculateTicketPrice(String staFrom, String staTo, int duration);
   public void payOrder(String orderId);
   public void markOrderAfterPaid(String orderId);
}
