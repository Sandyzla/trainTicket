package com.ticket.controller;

import com.ticket.collection.OrderForm;
import com.ticket.collection.OrderRequest;
import com.ticket.collection.OrderTicketQuery;
import com.ticket.collection.OrderView;
import com.ticket.dao.OrderDao;
import com.ticket.dao.TicketDao;
import com.ticket.entity.Order;
import com.ticket.entity.RemainTicket;
import com.ticket.entity.Ticket;
import com.ticket.help.BaseException;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseArray;
import com.ticket.help.OrderSubmittedResponse;
import com.ticket.service.OrderService;
import com.ticket.service.PaymentService;
import com.ticket.service.SeatService;
import com.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {
   @Autowired
   private OrderDao orderDao;
   @Autowired
   private OrderService orderService;
   @Autowired
   private TicketService ticketService;
   @Autowired
   private TicketDao ticketDao;
   @Autowired
   private SeatService seatService;
   @Autowired
   private PaymentService paymentService;

   /***
    * 订单提交(核心业务)
    */
   @RequestMapping("/order_submit")
   public OrderSubmittedResponse submitOrder(@RequestBody OrderRequest request) {
      try {

//        4.附加项
//        4-2 redis缓存（暂未实现）


         orderService.checkOrderForm(request);

//        3.数据库插入
//        3-1计算不同票型价格（实现）
//        3-2按照用户偏好选择位置(暂未实现)
//        3-3按照就近原则选择同伙位置(暂未实现)
//        3-4在无座的座位中随机分配座位(实现)
         orderService.markSeatOccupiedAndAddTicket(request);
         System.out.println(("用户购票：前端传输 " + request.toString()));
         return new OrderSubmittedResponse("success");
      } catch (BaseException e) {
         return new OrderSubmittedResponse(e.getCode(), e.getMessage());
      }
   }

   //    获取已提交的订单（订单中的车票信息通过Ticket控制层实现）
   @RequestMapping("/order_submitted")
   public BaseResponseArray<OrderView> getSubmittedOrder(@RequestBody String idNum) {
      return orderService.getOrderBasicInf(idNum, "submitted");
   }

   //    查看已支付的订单（同上）
   @RequestMapping("/order_paid")
   public BaseResponseArray<OrderView> getPaidOrder(@RequestBody String idNum) {
      return orderService.getOrderBasicInf(idNum, "paid");
   }

   //    订单取消
   @RequestMapping("/order_cancel")
   public BaseResponse cancelOrderByOrderId(@RequestBody String orderId) {
      try {
         List<Ticket> list = ticketDao.findTicketByOrderId(orderId);
         seatService.freeOrderSeatsByTickets(list);
         ticketService.deleteOrderTickets(list);
         orderDao.deleteOneOrder(orderId);
         return new BaseResponse("success");
      } catch (BaseException e) {
         return new BaseResponse("fail", e.getMessage());
      }
   }
   //订单支付
   @RequestMapping("/order_pay")
   public BaseResponse payOrderByOrderId(@RequestBody String orderId) {
      try {
         paymentService.payOrder(orderId);
         paymentService.markOrderAfterPaid(orderId);
         return new BaseResponse("success");
      } catch (BaseException e) {
         return new BaseResponse(e.getCode(), e.getMessage());
      }
   }

}