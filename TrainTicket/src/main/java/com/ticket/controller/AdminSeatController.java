package com.ticket.controller;

import com.ticket.collection.AdminCarriage;
import com.ticket.dao.OrderDao;
import com.ticket.dao.TicketDao;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseArray;
import com.ticket.service.AdminSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AdminSeatController {
    @Autowired
    private AdminSeatService adminSeatService;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private OrderDao orderDao;
    @RequestMapping("/admin/searchSeat")
    //尝试更新
    public BaseResponseArray<AdminCarriage> searchTrainSeat(@RequestBody String trainId){
       return adminSeatService.getAdminUpdateSeat(trainId);
    }

    @RequestMapping("/admin/rectifySeat")
    public BaseResponse rectifySeat(@RequestBody List<AdminCarriage> lists)
    {
        return adminSeatService.adminRectifySeat(lists);
    }

    @RequestMapping("admin/clearSeatCarr")
    public void clearSeatCarr(@RequestBody String trainId){

        adminSeatService.clearSeatCarr(trainId);
    }
//供测试使用,快速重置某一车次相关的所有order、seat、ticket、carriage(车次的时刻表是不能改的)
    @RequestMapping("admin/reset_trainId")
    public void testAdmin_1(@RequestBody String trainId){
//清理数据
        List<String>orderIdList = ticketDao.selectOrderIdByTrainId(trainId);
        for(String orderId:orderIdList){
            orderDao.deleteOneOrder(orderId);
        }
        ticketDao.deleteTicketByTrainId(trainId);
        adminSeatService.clearSeatCarr(trainId);
    }
}
