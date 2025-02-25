package com.ticket.service;

import com.ticket.dao.CarriageDao;
import com.ticket.dao.SeatDao;
import com.ticket.entity.RemainTicket;
import com.ticket.entity.Seat;
import com.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

   @Autowired
   private SeatDao seatDao;
   @Autowired
   private TimeListService timeListService;
   @Autowired
   private CarriageDao carriageDao;
   @Override
   public Map<String, List<Seat>> getSeats(String trainId, int noFrom, int noTo) {
      Map<String, List<Seat>> seats = new HashMap<>();
      List<Seat> afterFilteredlist;
      afterFilteredlist = getSeatsMatchNumOfStation(seatDao.getSeatsBySeatType(trainId, noFrom, noTo, "商务座"), noFrom, noTo);
      seats.put("商务座", afterFilteredlist);
      afterFilteredlist = getSeatsMatchNumOfStation(seatDao.getSeatsBySeatType(trainId, noFrom, noTo, "一等座"), noFrom, noTo);
      seats.put("一等座", afterFilteredlist);
      afterFilteredlist = getSeatsMatchNumOfStation(seatDao.getSeatsBySeatType(trainId, noFrom, noTo, "二等座"), noFrom, noTo);
      seats.put("二等座", afterFilteredlist);
      return seats;
   }

   public List<Seat> getSeatsMatchNumOfStation(List<Seat> seats, int noFrom, int noTo) {
      int requiredCount = noTo - noFrom;
      // Grouping seats by carr, sRow, and sCol
      Map<String, List<Seat>> groupedSeats = seats.stream()
            .collect(Collectors.groupingBy(s -> s.getCarr() + "-" + s.getsRow() + "-" + s.getsCol()));
      // Filtering groups that match the required count
      List<Seat> filteredSeats = new ArrayList<>();
      for (List<Seat> group : groupedSeats.values()) {
         if (group.size() == requiredCount) {
            filteredSeats.addAll(group);
         }
      }

      return filteredSeats;
   }

   @Override
   public Map<String, Integer> getSeatCount(String trainId, int noFrom, int noTo) {
      Map<String, Integer> seatCounts = new HashMap<>();
      List<RemainTicket> remainTickets = seatDao.getRemainTicket(trainId, noFrom, noTo);

      for (RemainTicket ticket : remainTickets) {
         seatCounts.put(ticket.getTicketType(), ticket.getTicketNum());
      }

      return seatCounts;
   }

   @Override
   public void freeOrderSeatsByTickets(List<Ticket> tickets) {
//      先通过站点找到noFrom,noTo
      Ticket ticketExample = tickets.get(0);
      int[] staNo = timeListService.findNoByStation(ticketExample.getTrainId(), ticketExample.getStaFrom(), ticketExample.getStaTo());
      for(Ticket ticket : tickets) {
         freeSeatByTicket(ticket, staNo[0], staNo[1]);
      }
   }

   @Override
   public void freeSeatByTicket(Ticket ticket, int noFrom, int noTo) {
      for(int i = noFrom; i < noTo; i++) {
         seatDao.updateSeatBySetN(ticket.getTrainId(), i, ticket.getCarr(), ticket.getsRow(), ticket.getsCol());
      }
   }


}
