package com.spring.service;

import java.util.List;

import com.spring.model.Teacher;

public interface TeacherService {
	List<Teacher> getAll();

	Teacher selectBytno(String tno);

	int updatePassword(String tno, String tpassword);

	int addTeacher(String tno, String tname, String tpassword);

	List<Teacher> selectListBytno(String tno);

	List<Teacher> selectListByname(String tname);

	int deleteTeacher(int tid);

	int updateTeacher(String tno, String tname);
}
