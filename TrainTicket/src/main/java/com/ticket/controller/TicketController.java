package com.ticket.controller;

import com.ticket.collection.TicketView;
import com.ticket.dao.TicketDao;
import com.ticket.entity.Ticket;
import com.ticket.help.BaseResponseArray;
import com.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TicketController {

   @Autowired
   private TicketService ticketService;

//   查看我的车票
   @RequestMapping("/my_ticket")
   public BaseResponseArray<TicketView> gainTickets(@RequestBody String idNum) {
      return new BaseResponseArray<>("success", ticketService.getMyTickets(idNum));
   }

   //   查看订单中的所有车票
   @RequestMapping("/order_ticket")
   public BaseResponseArray<TicketView> getTicketByOrderId(@RequestBody String orderId) {
      return new BaseResponseArray<>("success", ticketService.getOrderTickets(orderId));
   }
}