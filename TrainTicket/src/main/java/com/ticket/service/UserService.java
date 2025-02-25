package com.ticket.service;

import com.ticket.entity.User;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseData;

public interface UserService {
    public BaseResponseData<User> checkUser(String id, String ps);
    public BaseResponse registerIdCheck(String idNum);
    public BaseResponse registerUser(User user);
    public BaseResponse rectifyUser(User user);
}
