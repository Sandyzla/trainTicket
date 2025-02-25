package com.ticket.controller;

import com.ticket.collection.FromToQuery;
import com.ticket.entity.FromToList;
import com.ticket.service.FromToListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class FromToListController {
    @Autowired
    private FromToListService fromToListService;

    @RequestMapping("/fromTo")
    public List<FromToList> generateFromToList(@RequestBody FromToQuery query){
        System.out.println("用户查询：" + query.getFrom() + "至" + query.getTo());
        return fromToListService.findListByFromTo(query.getFrom(), query.getTo());

    }

//    @RequestMapping("/fromTo")
//    public

}
