package com.spring.dao;

import java.util.List;

import com.spring.model.Teacher;

public interface TeacherMapper {
	public List<Teacher> getAll();

	public Teacher selectBytno(String tno);

	int updatePassword(String tno, String tpassword);

	int addTeacher(String tno, String tname, String tpassword);

	List<Teacher> selectListBytno(String tno);

	List<Teacher> selectListByname(String tname);

	int deleteTeacher(int tid);

	int updateTeacher(String tno, String tname);
}