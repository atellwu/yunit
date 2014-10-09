package com.yeahmobi.yunit.sample.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yeahmobi.yunit.sample.dao.PersonDao;
import com.yeahmobi.yunit.sample.entity.Person;
import com.yeahmobi.yunit.sample.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Resource
	private PersonDao personDao;

	public Person get(Integer id) {
		return this.personDao.selectByPrimaryKey(id);
	}

	public PersonDao getPersonDao() {
		return this.personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
