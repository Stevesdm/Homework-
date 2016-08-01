package com.spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.TeacherMapper;
import com.spring.model.Teacher;
import com.spring.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource
	public TeacherMapper teacherMapper = null;

	@Override
	public List<Teacher> getAll() {
		return this.teacherMapper.getAll();
	}

	@Override
	public Teacher selectBytno(String tno) {
		return this.teacherMapper.selectBytno(tno);
	}

	@Override
	public int updatePassword(String tno, String tpassword) {
		return this.teacherMapper.updatePassword(tno, tpassword);
	}

	@Override
	public int addTeacher(String tno, String tname, String tpassword) {
		return this.teacherMapper.addTeacher(tno, tname, tpassword);
	}

	@Override
	public List<Teacher> selectListBytno(String tno) {
		return this.teacherMapper.selectListBytno(tno);
	}

	@Override
	public List<Teacher> selectListByname(String tname) {
		return this.teacherMapper.selectListByname(tname);
	}

	@Override
	public int deleteTeacher(int tid) {
		return this.teacherMapper.deleteTeacher(tid);
	}

	@Override
	public int updateTeacher(String tno, String tname) {
		return this.teacherMapper.updateTeacher(tno, tname);
	}
}
