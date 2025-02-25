package com.ticket.service;

import com.ticket.dao.UserDao;
import com.ticket.entity.User;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private UserDao userDao;

   @Override
   public BaseResponseData<User> checkUser(String id, String ps) {
      User user = userDao.getUserById(id);
      BaseResponseData<User> response;
      if (user == null) {
         response = new BaseResponseData<>("fail", "无该账号");
      } else if (ps.equals(user.getPassword())) {
         response = new BaseResponseData<>("user", user);
      } else {
         response = new BaseResponseData<>("fail", "密码错误");
      }
      return response;
   }

   @Override
   public BaseResponse registerIdCheck(String idNum) {
      User user = userDao.getUserById(idNum);
//        System.out.println(user.toString());
      if (user == null) {
         return new BaseResponse("success");
      } else {
         return new BaseResponse("fail");
      }
   }

   @Override
   public BaseResponse registerUser(User user) {
      userDao.addUser(user);
      return new BaseResponse("success", "成功注册");
   }

   @Override
   public BaseResponse rectifyUser(User user) {
      userDao.updateByUser(user);
      return new BaseResponse("success");
   }
}
