package com.ticket.dao;

import com.ticket.entity.Order;
import com.ticket.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {
   void insertOne(@Param("order") Order order);
   List<Order> findOrderSubmittedByIdNum(@Param("idNum") String idNum);

   List<Order> findOrderPaidByIdNum(@Param("idNum") String idNum);
   Order selectOneOrder(@Param("orderId")String orderId);
   void deleteOneOrder(@Param("orderId")String orderId);
   void updateOrderAfterPaid(@Param("orderId")String orderId);

}
