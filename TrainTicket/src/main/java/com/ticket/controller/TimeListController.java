package com.ticket.controller;

import com.ticket.collection.TrainPass;
import com.ticket.entity.TimeList;
import com.ticket.service.TimeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TimeListController {
    @Autowired
    private TimeListService timeListService;
    @RequestMapping("/timeListPass")
    public List<TimeList> generateFromToList(@RequestBody TrainPass query){
        System.out.println("列车详情页面请求：" + query.getTrainId() + " " + query.getNoFrom()
        + "->" + query.getNoTo());
        return timeListService.findOneTrainAllInf(query.getTrainId());
    }
}
