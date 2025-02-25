package com.ticket.service;

import com.ticket.dao.TimeListDao;
import com.ticket.entity.TimeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class TimeListServiceImpl implements TimeListService {
   @Autowired
   private TimeListDao timeListDao;

   @Override
   public List<TimeList> findOneTrainAllInf(String trainId) {
      TimeList timeListBegin = timeListDao.getTimeListByTrainId(trainId);
      int dif = timeListBegin.getGrow() - 1;
      return timeListDao.getTimeListByDif(dif);
   }

   @Override
   public List<TimeList> findOneTrainPassInf(String trainId, int noFrom, int noTo) {

      List<TimeList> allInf = findOneTrainAllInf(trainId);
      return allInf.subList(noFrom - 1, noTo);
   }

   @Override
   public int findCountByOneTrain(String trainId) {
      TimeList timeList = timeListDao.getTimeListByTrainId(trainId);
      int dif = timeList.getGrow() - 1;
      return timeListDao.FindCountByOneTrain(dif);
   }

   @Override
   public Time findTrainGoTime(String trainId, String staName)//补充userTicket的发车时间信息
   {
      List<TimeList> lists = findOneTrainAllInf(trainId);
      for (TimeList list : lists) {
         if (list.getStaName().equals(staName)) {
            return list.gettGo();
         }
      }
      return null;
   }

   @Override
   public String[] findStationByNo(String trainId, int noFrom, int noTo) {
      List<TimeList> timeLists = findOneTrainAllInf(trainId);
      String staFrom = "";
      String staTo = "";
      for (TimeList l : timeLists) {
         if (l.getNo() == noFrom) staFrom = l.getStaName();
         if (l.getNo() == noTo) staTo = l.getStaName();
      }
      return new String[]{staFrom, staTo};
   }

   @Override
   public int[] findNoByStation(String trainId, String staFrom, String staTo) {
      int[] staNo = new int[2];
      List<TimeList> timeLists = findOneTrainAllInf(trainId);
      for (int i = 0; i < timeLists.size(); i++) {
         if (timeLists.get(i).getStaName().equals(staFrom)) {
            staNo[0] = i + 1;
         } else if (timeLists.get(i).getStaName().equals(staTo)) {
            staNo[1] = i + 1;
         }
      }
      return staNo;
   }
}
