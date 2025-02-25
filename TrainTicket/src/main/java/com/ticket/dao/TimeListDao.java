package com.ticket.dao;

import com.ticket.entity.TimeList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TimeListDao {
    public List<TimeList> getTimeListByDif(int dif); //由grow-no确定与同一车次的记录
    public TimeList getTimeListByTrainId(String trainId); //获取no=1的记录
    public int FindCountByOneTrain(int dif);
}
