package com.ty.qa;

import com.ty.qa.dao.QuestionDao;
import com.ty.qa.dao.UserDao;
import com.ty.qa.model.Question;
import com.ty.qa.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class InitDatabaseTests {
	@Autowired
	UserDao userDao;

	@Test
	public void initDatabase() {
		Random random = new Random();
		for(int i = 0; i < 11; i++) {
			User user =new User();
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
			user.setName(String.format("USER%d", i));
			user.setPassword("");
			user.setSalt("");
			userDao.addUser(user);

			user.setPassword("xx");
			userDao.updatePassword(user);
		}

		Assert.assertEquals("xx", userDao.selectByid(1).getPassword());
		userDao.deleteById(1);
		Assert.assertNull(userDao.selectByid(1));
	}

}
