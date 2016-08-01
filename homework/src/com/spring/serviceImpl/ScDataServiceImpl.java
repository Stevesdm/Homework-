package com.spring.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.ScDataMapper;
import com.spring.model.ScData;
import com.spring.service.ScDataService;

@Service("scDataService")
public class ScDataServiceImpl implements ScDataService {

	@Resource
	public ScDataMapper scDataMapper = null;

	@Override
	public List<ScData> getCLassByCno(String cno) {
		return this.scDataMapper.getCLassByCno(cno);
	}

	@Override
	public List<ScData> getMajorDistinct() {
		return this.scDataMapper.getMajorDistinct();
	}

	@Override
	public List<ScData> getGradeDistinct() {
		return this.scDataMapper.getGradeDistinct();
	}

	@Override
	public List<ScData> getClassDistinct() {
		return this.scDataMapper.getClassDistinct();
	}

	@Override
	public List<ScData> getAll() {
		return this.scDataMapper.getAll();
	}

	@Override
	public int deleteByid(int id) {
		return this.scDataMapper.deleteByid(id);
	}

	@Override
	public int addSetCourse(ScData s) {
		return this.scDataMapper.addSetCourse(s);
	}

	@Override
	public int addMajorGradeClass(ScData s) {
		return this.scDataMapper.addMajorGradeClass(s);
	}

	@Override
	public List getStudentCourse(String major, String grade) {
		return this.scDataMapper.getStudentCourse(major,grade);
	}

}
