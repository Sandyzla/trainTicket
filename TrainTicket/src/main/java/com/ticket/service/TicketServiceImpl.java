package com.ticket.service;

import com.ticket.collection.TicketView;
import com.ticket.dao.TicketDao;
import com.ticket.dao.UserDao;
import com.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ticket.help.TimeComponent.convertMinutesToHoursAndMinutes;

@Service
public class TicketServiceImpl implements TicketService {

   @Autowired
   private TicketDao ticketDao;
   @Autowired
   private FromToListService fromToListService;
   @Autowired
   private UserDao userDao;

   @Override
   public void deleteOrderTickets(List<Ticket> tickets) {
      for (Ticket ticket : tickets) {
         deleteTicket(ticket);
      }
   }

   @Override
   public void deleteTicket(Ticket ticket) {
      ticketDao.deleteTicket(ticket);
   }

   @Override
   public List<TicketView> getMyTickets(String idNum) {
      List<Ticket> ticketList = ticketDao.selectTicketByIdNum(idNum);
      return convertTicketToTicketView(ticketList);
   }

   @Override
   public List<TicketView> getOrderTickets(String orderId) {
      List<Ticket> ticketList = ticketDao.findTicketByOrderId(orderId);
      return convertTicketToTicketView(ticketList);
   }

   @Override
   public List<TicketView> convertTicketToTicketView(List<Ticket> ticketList) {
      List<TicketView> ticketViewList = new ArrayList<>();
      for (Ticket ticket : ticketList) {
         int duration = fromToListService.getDuration(ticket.gettTo(),ticket.gettFrom());
         String durationHM = convertMinutesToHoursAndMinutes(duration);
         String passengerName = userDao.getUserById(ticket.getIdNum()).getUserName();
         String seatCol = getSeatCol(ticket.getsCol(), ticket.getSeatType());
         ticketViewList.add(new TicketView(ticket.getOrderId(), passengerName, ticket.getTrainId(), ticket.getStaFrom(), ticket.gettFrom()
               , ticket.getStaTo(), ticket.gettTo(), durationHM, ticket.getCarr(), ticket.getsRow(), seatCol, ticket.getSeatType(), ticket.getPrice()));
      }
      return ticketViewList;
   }


   @Override
   public String getSeatCol(int col, String seatType) {
      if (seatType.equals("商务座")) {
         return switch (col) {
            case 1 -> "A";
            case 2 -> "C";
            case 3 -> "F";
            default -> "";
         };
      } else if (seatType.equals("一等座")) {
         return switch (col) {
            case 1 -> "A";
            case 2 -> "C";
            case 3 -> "D";
            case 4 -> "F";
            default -> "";
         };
      } else {
         return switch (col) {
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "C";
            case 4 -> "D";
            case 5 -> "F";
            default -> "";
         };
      }
   }
}
