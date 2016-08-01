package com.spring.dao;

import java.util.List;

import com.spring.model.Course;

public interface CourseMapper {

	public List<Course> getCourseByGrade(String major, String grade);

	public List<Course> getCourseByTno(String tno);

	public Course selectBycno(String cno);

	public int addCourse(Course c);

	public List<Course> selectListBycno(String cno);

	public List<Course> selectListByname(String cname);

	public int deleteCourse(int cid);

	public int updateCourse(Course c);

	public List<Course> getAllCno();
	
	public int addSetCourse(Course c);
}