package com.spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.CourseMapper;
import com.spring.model.Course;
import com.spring.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource
	CourseMapper courseMapper = null;

	@Override
	public List<Course> getCourseByGrade(String major, String grade) {
		return this.courseMapper.getCourseByGrade(major, grade);
	}

	@Override
	public Course selectBycno(String cno) {
		return this.courseMapper.selectBycno(cno);
	}

	@Override
	public List<Course> getCourseByTno(String tno) {
		return this.courseMapper.getCourseByTno(tno);
	}

	@Override
	public int addCourse(Course c) {
		return this.courseMapper.addCourse(c);
	}

	@Override
	public List<Course> selectListBycno(String cno) {
		return this.courseMapper.selectListBycno(cno);
	}

	@Override
	public List<Course> selectListByname(String cname) {
		return this.courseMapper.selectListByname(cname);
	}

	@Override
	public int deleteCourse(int cid) {
		return this.courseMapper.deleteCourse(cid);
	}

	@Override
	public int updateCourse(Course c) {
		return this.courseMapper.updateCourse(c);
	}

	@Override
	public List<Course> getAllCno() {
		return this.courseMapper.getAllCno();
	}

	@Override
	public int addSetCourse(Course c) {
		return this.courseMapper.addSetCourse(c);
	}

}
