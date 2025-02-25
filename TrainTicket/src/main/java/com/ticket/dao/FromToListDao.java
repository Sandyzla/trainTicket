package com.ticket.dao;

import com.ticket.entity.FromToList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface FromToListDao {


    public List<FromToList> getByFromTo(@Param("from") String from, @Param("to") String to); //FromTo查询


}
