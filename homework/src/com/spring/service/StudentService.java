package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.model.Student;

public interface StudentService {
	public List<Student> getAllStudents();

	public Student selectBysno(String sno);

	int updatePassword(String sno, String spassword);

	List<String> getClassByMajorGrade(String smajor, String sgrade);

	int selectUnupload(Map<String, Object> map);

	List<Student> selectUnuploadByPage(Map<String, Object> map);

	int addStudent(Student s);

	List<Student> selectListBysno(String sno);

	List<Student> selectListByMajorClass(String smajor, String sclass);

	int deleteStudent(int sid);

	int updateStudentInfo(Student s);

	int resetStuPwd(int sid);
}
