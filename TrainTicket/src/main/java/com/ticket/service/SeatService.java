package com.ticket.service;

import com.ticket.entity.Seat;
import com.ticket.entity.Ticket;

import java.util.List;
import java.util.Map;

public interface SeatService {
   public Map<String, List<Seat>> getSeats(String trainId, int noFrom, int noTo);

   public List<Seat> getSeatsMatchNumOfStation(List<Seat> seats, int noFrom, int noTo);

   public Map<String, Integer> getSeatCount(String trainId, int noFrom, int noTo);

   public void freeOrderSeatsByTickets(List<Ticket> tickets);

   void freeSeatByTicket(Ticket ticket, int noFrom, int noTo);
}
