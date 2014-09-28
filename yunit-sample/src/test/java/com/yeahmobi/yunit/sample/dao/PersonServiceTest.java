package com.yeahmobi.yunit.sample.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.yeahmobi.ut_sample.dao.PersonMapper;
import com.yeahmobi.ut_sample.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class PersonServiceTest {

	@Autowired
	private PersonMapper personMapper;

	@Test
	@DatabaseSetup
	public void testGet() throws Exception {
		Person person = personMapper.selectByPrimaryKey(1);
		Assert.assertEquals("name1", person.getName());
	}

	@Test
	@DatabaseSetup
	@ExpectedDatabase
	public void testInsert() throws Exception {
		Person person = new Person();
		person.setId(3);
		person.setName("name3");
		person.setGender("f");
		personMapper.insert(person);
	}

	@Test
	@DatabaseSetup
	@ExpectedDatabase
	public void testUpdate() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setName("name111");
		personMapper.updateByPrimaryKeySelective(person);
	}

	@Test
	@DatabaseSetup
	@ExpectedDatabase
	public void testDelete() throws Exception {
		personMapper.deleteByPrimaryKey(2);
	}

}
