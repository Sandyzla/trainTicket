package com.ticket.controller;

import com.ticket.collection.UserLogin;
import com.ticket.entity.User;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseData;
import com.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController

public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public BaseResponseData<User> loginCheck(@RequestBody UserLogin userLogin){
        System.out.println("用户登录请求: " + userLogin.toString());
        return userService.checkUser(userLogin.getId(),userLogin.getPs());
    }

    @RequestMapping("/registerListen")
    public BaseResponse registerIdCheck(@RequestBody String idNum){
        return userService.registerIdCheck(idNum);
    }
    @RequestMapping("/register")
    public BaseResponse register(@RequestBody User user){
        return userService.registerUser(user);
    }

    @RequestMapping("rectifyUser")
    public BaseResponse changeUserInf(@RequestBody User user){
        return userService.rectifyUser(user);
    }
}
