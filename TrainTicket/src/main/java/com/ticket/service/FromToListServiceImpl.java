package com.ticket.service;

import com.ticket.collection.SeatNumAndPrice;
import com.ticket.dao.FromToListDao;
import com.ticket.dao.SeatDao;
import com.ticket.entity.FromToList;
import com.ticket.entity.RemainTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FromToListServiceImpl implements FromToListService {
    @Autowired
    private FromToListDao fromToListDao;
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private TimeListService timeListService;
    @Autowired
    private PaymentService paymentService;

    @Override
    public List<FromToList> findListByFromTo(String from, String to) {
        //查询 车次信息
        List<FromToList> lists = fromToListDao.getByFromTo(from, to);
       for (FromToList list : lists) {
          //赋为-1，即无该票型
          Map<String, SeatNumAndPrice> seatMap = new HashMap<>();
          seatMap.put("商务座", new SeatNumAndPrice(-1, 0));
          seatMap.put("一等座", new SeatNumAndPrice(-1, 0));
          seatMap.put("二等座", new SeatNumAndPrice(-1, 0));
          int duration = getDuration(list.gettArrive(), list.gettGo());
          list.setDuration(duration);
          Map<String, Integer> seatPrices = paymentService.calculateTicketPrice(list.getStaFrom(), list.getStaTo() ,duration);
          //赋为0,即存在该票型
          List<String> existTypes = seatDao.getExistSeatTypeByCarriage(list.getTrainId());
          for (String type : existTypes) {
             seatMap.put(type, new SeatNumAndPrice(0, seatPrices.get(type)));
          }
          //查询 余票信息
          List<RemainTicket> remainTicketList = seatDao.getRemainTicket(list.getTrainId(),
                list.getNoFrom(), list.getNoTo());
          //将 余票信息 整合到 车次信息 中
          for (RemainTicket remainTicket : remainTicketList) {
             String type = remainTicket.getTicketType();
             int num = remainTicket.getTicketNum();
             seatMap.put(type, new SeatNumAndPrice(num, seatPrices.get(type)));
          }
          list.setSeatMap(seatMap);
       }
        return lists;
    }

   @Override
   public int getDuration(Time tArrive, Time tGo) {

      LocalTime startLocalTime = tArrive.toLocalTime();
      LocalTime endLocalTime = tGo.toLocalTime();
      // 如果结束时间早于开始时间，说明跨天，需要加一天
      if (endLocalTime.isBefore(startLocalTime)) {
         return (int)Duration.between(endLocalTime, startLocalTime).toMinutes();
      }else{
         return 24 * 60 - (int)Duration.between(startLocalTime, endLocalTime).toMinutes();
      }
      // 计算两个时间点之间的分钟差
   }
}
