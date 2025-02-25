package com.ticket;

import com.ticket.dao.UserDao;
import com.ticket.entity.User;
import com.ticket.help.BaseResponseData;
import com.ticket.service.UserService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.comparator.JSONCompareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SpringBootTest
class TrainTicketApplicationTests {

   @Qualifier("userDao")
   @Autowired
   private UserDao userDao;

	@Test
	void contextLoads() {
		User user = userDao.getUserById("330324200203292278");
	}

}
