package com.ticket.service;

import com.ticket.collection.TicketView;
import com.ticket.entity.Ticket;

import java.util.List;

public interface TicketService {
   public void deleteOrderTickets(List<Ticket> tickets);
   public void deleteTicket(Ticket ticket);
   public List<TicketView> getMyTickets(String idNum);
   public List<TicketView> getOrderTickets(String orderId);
   public List<TicketView> convertTicketToTicketView(List<Ticket> ticketList);
   public String getSeatCol(int col, String seatType);
}
