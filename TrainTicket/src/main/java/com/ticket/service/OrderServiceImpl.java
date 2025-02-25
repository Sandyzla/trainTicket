package com.ticket.service;

import com.ticket.collection.OrderRequest;
import com.ticket.collection.OrderForm;
import com.ticket.collection.OrderView;
import com.ticket.dao.OrderDao;
import com.ticket.dao.SeatDao;
import com.ticket.dao.TicketDao;
import com.ticket.dao.UserDao;
import com.ticket.entity.Order;
import com.ticket.entity.Seat;
import com.ticket.entity.Ticket;
import com.ticket.help.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

import static com.ticket.help.TimeComponent.convertMinutesToHoursAndMinutes;

@Service
public class OrderServiceImpl implements OrderService {

   @Autowired
   private TicketDao ticketDao;
   @Autowired
   private OrderDao orderDao;
   @Autowired
   private SeatDao seatDao;
   @Autowired
   private TimeListService timeListService;
   @Autowired
   private PaymentService paymentService;
   @Autowired
   private TimeComponent timeComponent;
   @Autowired
   private SeatService seatService;
   @Autowired
   private FromToListService fromToListService;
   @Autowired
   private UserDao userDao;

   @Override
   public void checkOrderForm(OrderRequest request) {
      try {
         //        1.输入合法性检查
//        1-1.正则匹配(前端实现)
//        1-2.重复身份证(暂未实现)
         //        2.数据库检查
//        2-1.身份证与名字对应关系检查（暂未实现，现在只用身份证购票）
//        2-2.重复订单【某人重复出现在同一车次的订单中】
//         2-3重复提交
         Set<String> idSet = new HashSet<>();
         List<List<OrderForm>> orderForms = request.getLists();
         for(List<OrderForm> orderFormList : orderForms){
            for (OrderForm orderForm : orderFormList) {
               if (!idSet.add(orderForm.getIdNum())) {
                  // 如果添加失败，表示有重复的idNum
                  throw new BaseException("fail", "订单记录重复");
               }
               System.out.println(orderForm.getIdNum());
               List<Ticket> tickets = ticketDao.selectAnyTicketByIdNum(orderForm.getIdNum());
//               System.out.println("size = " + tickets.size());
               for (Ticket ticket : tickets) {
//                  System.out.println(ticket.toString());
                  if (ticket.getTrainId().equals(request.getTrainId())) {
                     throw new BaseException("fail", "数据库：某个用户已有该车次行程");
                  }
               }
            }
         }
//


//         2-4余票检查
         Map<String, Integer> seatCounts = seatService.getSeatCount(request.getTrainId(), request.getNoFrom(), request.getNoTo());

         for (List<OrderForm> orderListForOneSeatType : orderForms) {
            if (!orderListForOneSeatType.isEmpty() && seatCounts.get(orderListForOneSeatType.get(0).getSeatType()) < orderListForOneSeatType.size()) {
               throw new BaseException("no_ticket", "余票不足");
            }
         }
      } catch (BaseException e) {
         throw new BaseException(e.getCode(), e.getMessage());
      }
   }

   @Transactional
   @Override
   public void markSeatOccupiedAndAddTicket(OrderRequest request) {
      //获取基本数据
      String train_id = request.getTrainId();
      int no_from = request.getNoFrom();
      int no_to = request.getNoTo();
      int num_of_station = no_to - no_from;
      String sta_from = timeListService.findStationByNo(train_id, no_from, no_to)[0];
      String sta_to = timeListService.findStationByNo(train_id, no_from, no_to)[1];
      Time t_from = timeListService.findTrainGoTime(train_id, sta_from);
      Time t_to = timeListService.findTrainGoTime(train_id, sta_to);
      String order_id = UUID.randomUUID().toString();
      //获取不同票型的所有座位并随机分配座位

      Map<String, List<Seat>> seats = seatService.getSeats(train_id, no_from, no_to);
//         --------------------并发测试------------------
//         Thread.sleep(4000);
      int totalPrice = 0;
//         遍历每一种票型
      try {
         for (int i = 0; i < request.getLists().size(); i++) {
            List<OrderForm> orderRequestBySeatType = request.getLists().get(i); //每一种票型的所有乘客的订单请求
            //遍历所有请求，seat表更新，ticket表插入新记录
            for (int j = 0; j < orderRequestBySeatType.size(); j++) {
               OrderForm order = orderRequestBySeatType.get(j);
               String seatType = order.getSeatType();
               Seat seat = seats.get(seatType).get(j * num_of_station);
               totalPrice += order.getPrice();
               for (int k = no_from; k < no_to; k++) {
                  int updatedRow = seatDao.updateSeatBySetY(train_id, k, seat.getCarr(), seat.getsRow(), seat.getsCol(),
                        seat.getVersion());
                  if (updatedRow == 0) {
                     throw new BaseException();
                  }
               }
               ticketDao.insertTicket(new Ticket(order_id, order.getIdNum(), train_id,
                     sta_from, t_from, sta_to, t_to,
                     seat.getCarr(), seat.getsRow(), seat.getsCol(),
                     seatType, order.getPrice()));
            }
         }
         orderDao.insertOne(new Order(order_id, timeComponent.getCurrentSqlTime(), "submitted",
               request.getRequestIdNum(), totalPrice));
      } catch (DataAccessException e) {
         throw new BaseException("fail", "数据库：身份信息不存在");
      } catch (BaseException e) {
         throw new BaseException("fail", "数据库：服务器繁忙");
      }
   }

   @Override
   public BaseResponseArray<OrderView> getOrderBasicInf(String idNum, String state) {
      List<OrderView> orderViewList = new ArrayList<>();
      List<Order> orderList = switch (state) {
         case "submitted" -> orderDao.findOrderSubmittedByIdNum(idNum);
         case "paid" -> orderDao.findOrderPaidByIdNum(idNum);
         default -> new ArrayList<>();
      };
      for (Order order : orderList) {
         String orderId = order.getOrderId();
         Ticket ticket = ticketDao.findTicketByOrderId(orderId).get(0);
         String userName = userDao.getUserById(idNum).getUserName();
         int duration = fromToListService.getDuration(ticket.gettTo(), ticket.gettFrom());
         String durationHM = convertMinutesToHoursAndMinutes(duration);
         orderViewList.add(new OrderView(order.getOrderId(), userName, order.getCreateTime(), order.getState(), order.getPrice(),
               ticket.getTrainId(), ticket.getStaFrom(), ticket.gettFrom(), ticket.getStaTo(), ticket.gettTo(), durationHM));
      }
      return new BaseResponseArray<>("success", orderViewList);
   }
}
