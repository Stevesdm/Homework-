package com.spring.service;

import java.util.List;

import com.spring.model.Admin;

public interface AdminService {
	List<Admin> getAll();

	Admin selectByAno(String ano);

	int updatePassword(String ano, String apassword);
}
