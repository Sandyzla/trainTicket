package com.ticket.realm;

import com.ticket.dao.UserDao;
import com.ticket.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthenticatingRealm {

   @Autowired
   private UserDao userDao;

   //认证
   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
      UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
      User user = userDao.getUserById(token.getUsername());
      if(user != null){
         return new SimpleAuthenticationInfo(user,user.getPassword(),user.getUserName());
      }

      return null;
   }
}
