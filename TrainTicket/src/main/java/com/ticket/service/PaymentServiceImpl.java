package com.ticket.service;

import com.ticket.dao.OrderDao;
import com.ticket.dao.StationDao;
import com.ticket.dao.TimeListDao;
import com.ticket.dao.UserDao;
import com.ticket.entity.Order;
import com.ticket.entity.Station;
import com.ticket.entity.TimeList;
import com.ticket.entity.User;
import com.ticket.help.BaseException;
import com.ticket.help.TicketPriceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {


   @Autowired
   private TimeListService timeListService;
   @Autowired
   private StationDao stationDao;
   @Autowired
   private UserDao userDao;
   @Autowired
   private OrderDao orderDao;

   @Override
   public Map<String, Integer> calculateTicketPrice(String staFrom, String staTo, int duration) {
      //      获取TimeList站点序号对应的站点名称---开始'
      Station stationFrom = stationDao.getStationByName(staFrom);
      Station stationTo = stationDao.getStationByName(staTo);
      //      根据站点的经纬度粗略估算票价
      double distance = TicketPriceHelper.getDistance(stationFrom.getLongitude(), stationFrom.getLatitude(),
            stationTo.getLongitude(), stationTo.getLatitude());
      Map<String, Integer> priceMap = new HashMap<String, Integer>();
      priceMap.put("商务座", (int) TicketPriceHelper.computePrice(distance, duration, "商务座"));
      priceMap.put("一等座", (int) TicketPriceHelper.computePrice(distance, duration, "一等座"));
      priceMap.put("二等座", (int) TicketPriceHelper.computePrice(distance, duration, "二等座"));
      return priceMap;
   }

   @Override
   public void payOrder(String orderId) {
      Order order = orderDao.selectOneOrder(orderId);
      int totalCost = order.getPrice();
      String idNum = order.getIdNum();
      User user = userDao.getUserById(idNum);
      try {
         if (user.getMoney() < totalCost) throw new BaseException("fail", "余额不足，请充值");
         int updateRow = userDao.updateMoney(order.getIdNum(), user.getMoney() - totalCost, user.getVersion());
         if (updateRow == 0) {
            throw new BaseException("busy");
         }
      } catch (BaseException e) {
         throw new BaseException(e.getCode(), e.getMessage());
      }
   }

   @Override
   public void markOrderAfterPaid(String orderId) {
      orderDao.updateOrderAfterPaid(orderId);
   }
}
