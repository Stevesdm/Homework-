package com.spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.AdminMapper;
import com.spring.model.Admin;
import com.spring.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	AdminMapper adminMpper = null;

	@Override
	public List<Admin> getAll() {
		return this.adminMpper.getAll();
	}

	@Override
	public Admin selectByAno(String ano) {
		return this.adminMpper.selectByAno(ano);
	}

	@Override
	public int updatePassword(String ano, String apassword) {
		return this.adminMpper.updatePassword(ano, apassword);
	}

}
