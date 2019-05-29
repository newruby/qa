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

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class InitQuestionTests {

	@Autowired
	QuestionDao questionDao;


	@Test
	public void initQuestion() {
		Random random = new Random();
		for(int i = 0; i < 11; i++) {
			Question question = new Question();
			question.setCommentCount(i);
			Date date = new Date();
			date.setTime(date.getTime() + 1000*3600*i);
			question.setCreatedDate(date);
			question.setUserId(i+1);
			question.setTitle(String.format("TITLE{%d}", i));
			question.setContent(String.format("zzzzzzz Content %d", i));

			questionDao.addQuestion(question);
		}

		System.out.print(questionDao.selectLatestQuestions(0, 0, 10));


	}

}
