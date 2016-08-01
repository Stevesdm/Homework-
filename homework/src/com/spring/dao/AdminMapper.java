package com.spring.dao;

import java.util.List;

import com.spring.model.Admin;

public interface AdminMapper {
	List<Admin> getAll();

	Admin selectByAno(String ano);

	int updatePassword(String ano, String apassword);
}