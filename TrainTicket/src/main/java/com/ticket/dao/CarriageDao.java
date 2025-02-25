package com.ticket.dao;

import com.ticket.entity.Carriage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CarriageDao {
    public void insertCarriage(@Param("trainId")String trainId, @Param("carr") int carr,
                               @Param("seatType") String seatType);

    public List<Carriage> selectByTrainId(@Param("trainId")String trainId);

    public void deleteAllByTrainId(@Param("trainId") String trainId);
}
