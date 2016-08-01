package com.spring.dao;

import java.util.List;

import com.spring.model.ScData;

public interface ScDataMapper {

	public List<ScData> getCLassByCno(String cno);

	public List<ScData> getMajorDistinct();

	public List<ScData> getGradeDistinct();

	public List<ScData> getClassDistinct();

	public List<ScData> getAll();

	public int deleteByid(int id);

	public int addSetCourse(ScData s);

	public int addMajorGradeClass(ScData s);
	
	public List getStudentCourse(String major, String grade);
}