package com.ticket.dao;

import com.ticket.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TicketDao {
    public void insertTicket(@Param("ticket") Ticket ticket);

    public List<Ticket> selectTicketByIdNum(@Param("idNum") String idNum);
    public List<Ticket> selectAnyTicketByIdNum(@Param("idNum") String idNum);
    public void deleteTicketByTrainId(@Param("trainId") String trainId); //仅测试

    public List<String> selectOrderIdByTrainId(@Param("trainId") String trainId); //仅测试
    public List<Ticket> findTicketByOrderId(String orderId);

    public void deleteTicket(@Param("ticket") Ticket ticket);
}

