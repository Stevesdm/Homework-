package com.spring.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.StudentMapper;
import com.spring.model.Student;
import com.spring.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;

	@Override
	public List<Student> getAllStudents() {
		return this.studentMapper.getAllStudents();
	}

	@Override
	public Student selectBysno(String sno) {
		return this.studentMapper.selectBysno(sno);
	}

	@Override
	public int updatePassword(String sno, String spassword) {
		return this.studentMapper.updatePassword(sno, spassword);
	}

	@Override
	public List<String> getClassByMajorGrade(String smajor, String sgrade) {
		return this.studentMapper.getClassByMajorGrade(smajor, sgrade);
	}

	@Override
	public int selectUnupload(Map<String, Object> map) {
		return this.studentMapper.selectUnupload(map);
	}

	@Override
	public List<Student> selectUnuploadByPage(Map<String, Object> map) {
		return this.studentMapper.selectUnuploadByPage(map);
	}

	@Override
	public int addStudent(Student s) {
		return this.studentMapper.addStudent(s);
	}

	@Override
	public List<Student> selectListBysno(String sno) {
		return this.studentMapper.selectListBysno(sno);
	}

	@Override
	public List<Student> selectListByMajorClass(String smajor, String sclass) {
		return this.studentMapper.selectListByMajorClass(smajor, sclass);
	}

	@Override
	public int deleteStudent(int sid) {
		return this.studentMapper.deleteStudent(sid);
	}

	@Override
	public int updateStudentInfo(Student s) {
		return this.studentMapper.updateStudentInfo(s);
	}

	@Override
	public int resetStuPwd(int sid) {
		return this.studentMapper.resetStuPwd(sid);
	}
}
