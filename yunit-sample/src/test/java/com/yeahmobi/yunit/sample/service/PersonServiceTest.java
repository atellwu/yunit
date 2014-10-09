package com.yeahmobi.yunit.sample.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.yeahmobi.yunit.sample.dao.PersonDao;
import com.yeahmobi.yunit.sample.entity.Person;
import com.yeahmobi.yunit.sample.entity.PersonExample;

/**
 * 本示例的场景是：PersonService依赖了dao(PersonMapper)，因此对PersonService的单元测试，可以将PersonMapper进行mock。
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class PersonServiceTest {

	/*
	 * 注解@Mock和@InjectMocks的说明：
	 *
	 * @Mock修饰的personMapper实例，将会被自动注入到被@InjectMocks修饰的personService实例中，需要保证 PersonSeriveImpl有PersonMapper的setter方法
	 */
	@Mock
	PersonDao personDao;

	@InjectMocks
	@Autowired
	PersonService personService;

	@Before
	public void setup() {
		// mock的初始化
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 演示mock功能：mock方法返回值
	 */
	@Test
	public void testGet1() {
		// PersonService.get() 方法会调用 PersonMapper.selectByPrimaryKey() 方法
		// 因此需要对 PersonMapper.selectByPrimaryKey() 进行mock
		Person person = new Person();
		person.setName("test");
		Mockito.when(this.personDao.selectByPrimaryKey(Mockito.anyInt())).thenReturn(person);

		// personService.get(1)方法返回上一步mock的结果
		Assert.assertEquals(this.personService.get(1).getName(), "test");

	}

	/**
	 * 演示mock功能：同一个方法多次调用返回不同的结果
	 */
	@Test
	public void testGet2() {
		Person person = new Person();
		person.setName("test");
		// 使用多次thenReturn可以实现：第一次调用返回person，第二次返回null
		Mockito.when(this.personDao.selectByPrimaryKey(Mockito.anyInt())).thenReturn(null).thenReturn(person);

		// 调用2次，验证上述的mock
		Assert.assertNull(this.personService.get(1));
		Assert.assertEquals(this.personService.get(1).getName(), "test");

	}

	/**
	 * 演示mock功能：未被mock的方法将返回默认值
	 */
	@Test
	public void testGet3() {
		Person person = new Person();
		person.setName("test");
		Mockito.when(this.personDao.selectByPrimaryKey(Mockito.anyInt())).thenReturn(person).thenReturn(null);// 使用多次thenReturn可以实现：第一次调用返回person，第二次返回null

		// 以下代码只是为了告诉大家：mock对象会覆盖整个被mock的对象，因此没有mock的方法(countByExample),只能返回默认值0。
		PersonExample example = new PersonExample();
		Assert.assertTrue(this.personDao.countByExample(example) == 0);

	}
}
