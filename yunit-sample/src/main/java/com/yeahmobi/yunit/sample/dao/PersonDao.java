package com.yeahmobi.yunit.sample.dao;

import java.util.List;

import com.yeahmobi.yunit.sample.entity.Person;

public interface PersonDao extends PersonMapper {
	List<Person> selectAll();
}
