package com.ticket.service;

import com.ticket.entity.FromToList;

import java.sql.Time;
import java.util.List;

public interface FromToListService {

//    该方法根据出发,到达确定: 1.所有经过的车次 2.车次对应的余票信息
    List<FromToList> findListByFromTo(String from, String to);

    int getDuration(Time tArrive, Time tGo);
}
