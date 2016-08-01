package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.model.ScData;

public interface ScDataService {
	List<ScData> getCLassByCno(String cno);

	List<ScData> getMajorDistinct();

	List<ScData> getGradeDistinct();

	List<ScData> getClassDistinct();

	List<ScData> getAll();

	int deleteByid(int id);

	int addSetCourse(ScData s);

	int addMajorGradeClass(ScData s);
	
	List getStudentCourse(String major,String grade);
}
