package com.ticket.dao;

import com.ticket.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    User getUserById(@Param("id")String id);
    void addUser(@Param("user")User user) throws DataAccessException;
    void updateByUser(@Param("user")User user);
    int updateMoney(@Param("idNum")String idNum, @Param("money")int money, @Param("version")int version);
}
