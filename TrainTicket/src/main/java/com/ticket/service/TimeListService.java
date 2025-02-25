package com.ticket.service;

import com.ticket.entity.TimeList;

import java.sql.Time;
import java.util.List;

public interface TimeListService {
    List<TimeList> findOneTrainAllInf(String trainId); //根据车次号获取全部TimeList信息
    List<TimeList> findOneTrainPassInf(String trainId, int noFrom, int noTo); //根据车次号获取全部TimeList信息

    int findCountByOneTrain(String trainId); //获取列车的总计站数
    Time findTrainGoTime(String trainId, String staName); //补充userTicket界面的发车时间
    String[] findStationByNo(String trainId, int noFrom, int noTo);
    int[] findNoByStation(String trainId, String staFrom, String staTo);
}
