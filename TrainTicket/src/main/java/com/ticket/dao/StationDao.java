package com.ticket.dao;

import com.ticket.entity.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StationDao {
    public Station getStationByName(@Param("staName") String staName);
}
