package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.model.Admin;
import com.spring.model.Course;
import com.spring.model.Sc;
import com.spring.model.ScData;
import com.spring.model.Student;
import com.spring.model.Teacher;
import com.spring.service.AdminService;
import com.spring.service.CourseService;
import com.spring.service.ScDataService;
import com.spring.service.ScService;
import com.spring.service.StudentService;
import com.spring.service.TeacherService;
import com.spring.util.MD5Util;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource
	private AdminService adminService = null;

	@Resource
	private ScDataService scDataService = null;

	@Resource
	private StudentService studentService = null;

	@Resource
	private TeacherService teacherService = null;

	@Resource
	private CourseService courseService = null;

	@Resource
	private ScService scService = null;

	// 跳转到设置作业界面
	@RequestMapping(value = "/homepage")
	public String homepage() {
		return "admin/mainpage";
	}

	// 跳转到修改密码
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	public String changePwd(HttpSession session) {
		return "admin/changePwd";
	}

	// 修改密码
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(HttpSession session, HttpServletRequest req,
			Model model) {
		String newPassword = ((String) req.getParameter("password1")).trim();
		String newPasswordAgain = ((String) req.getParameter("password2"))
				.trim();

		System.out.println(newPassword);
		Admin a = (Admin) session.getAttribute("user");
		String ano = a.getAno();

		if (!newPassword.equals(newPasswordAgain) || newPassword.length() < 6
				|| newPassword.length() > 15) {
			model.addAttribute("message", "新密码信息填写错误");
			return "admin/changePwd";
		} else {
			adminService.updatePassword(ano, MD5Util.MD5(newPasswordAgain));
			model.addAttribute("message", "密码修改成功");
			return "teacher/changePwd";
		}
	}

	// 跳转到添加学生页面
	@RequestMapping(value = "/addStudent", method = RequestMethod.GET)
	public String addStudent(HttpSession session, Model model) {
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();

		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		return "admin/addStudent";
	}

	// 添加学生
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String AddStudent(HttpSession session, Model model,
			HttpServletRequest req) {
		String sno = req.getParameter("sno");
		String sname = req.getParameter("sname");
		String smajor = req.getParameter("smajor");
		String sgrade = req.getParameter("sgrade");
		String sclass = req.getParameter("sclass");
		String spassword = MD5Util.MD5("123456");

		Student s = new Student(sno, spassword, sname, smajor, sgrade, sclass);
		studentService.addStudent(s);
		model.addAttribute("message", "添加成功");

		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		return "admin/addStudent";
	}

	// 跳转到查询学生页面
	@RequestMapping(value = "/selectStudent", method = RequestMethod.GET)
	public String selectStudent(HttpSession session, Model model) {
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> classList = scDataService.getClassDistinct();

		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		return "admin/selectStudent";
	}

	// 按照学号查询学生
	@RequestMapping(value = "/selectStudentBysno", method = RequestMethod.POST)
	public String selectStudentBysno(HttpSession session, Model model,
			HttpServletRequest req) {
		String sno = req.getParameter("sno");
		List<Student> s = studentService.selectListBysno(sno);

		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> classList = scDataService.getClassDistinct();

		model.addAttribute("student", s);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		return "admin/selectStudent";
	}

	// 按照专业班级查询学生
	@RequestMapping(value = "/selectStudentByMajorClass", method = RequestMethod.POST)
	public String selectStudentBymajorclass(HttpSession session, Model model,
			HttpServletRequest req) {
		String smajor = req.getParameter("smajor");
		String sclass = req.getParameter("sclass");
		List<Student> s = studentService.selectListByMajorClass(smajor, sclass);

		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> classList = scDataService.getClassDistinct();

		model.addAttribute("student", s);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		return "admin/selectStudent";
	}

	// 在selectstudent中删除学生
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
	public String deleteStudent(HttpSession session, Model model, String sid) {
		int sidInt = Integer.parseInt(sid);

		studentService.deleteStudent(sidInt);

		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> classList = scDataService.getClassDistinct();

		model.addAttribute("message", "成功删除学生");
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		return "admin/selectStudent";
	}

	// 跳转到更改学生信息界面
	@RequestMapping(value = "/updateStudent", method = RequestMethod.GET)
	public String updateStudent(HttpSession session, Model model, String sno) {
		Student s = studentService.selectBysno(sno);
		model.addAttribute("student", s);

		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		return "admin/updateStudent";
	}

	// 更改学生信息
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public String UpdateStudent(HttpSession session, Model model, String sno,
			String sname, String smajor, String sgrade, String sclass) {
		Student s = new Student(sno, sname, smajor, sgrade, sclass);
		studentService.updateStudentInfo(s);

		model.addAttribute("message", "成功更新学生信息");
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		return "admin/updateStudent";
	}

	// 修改密码
	@RequestMapping(value = "/resetStudentPwd", method = RequestMethod.POST)
	public void resetStudentPwd(HttpSession session, Model model, String sid,
			HttpServletRequest req, HttpServletResponse res) {
		int sidInt = Integer.parseInt(sid);
		studentService.resetStuPwd(sidInt);
		try {
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
			PrintWriter out = res.getWriter();
			out.print("成功修改密码");
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 跳转到添加教师页面
	@RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
	public String addTeacher(HttpSession session, Model model) {
		return "admin/addTeacher";
	}

	// 添加教师
	@RequestMapping(value = "/AddTeacher", method = RequestMethod.POST)
	public String AddTeacher(HttpSession session, Model model, String tno,
			String tname) {
		String tpassword = MD5Util.MD5("000000");
		teacherService.addTeacher(tno, tname, tpassword);

		model.addAttribute("message", "成功添加教师");
		return "admin/addTeacher";
	}

	// 跳转到查找教师页面
	@RequestMapping(value = "/selectTeacher", method = RequestMethod.GET)
	public String selectTeacher(HttpSession session, Model model) {
		return "admin/selectTeacher";
	}

	// 按照教师号查找教师
	@RequestMapping(value = "/selectTeacherByTno", method = RequestMethod.POST)
	public String selectTeacherByTno(HttpSession session, Model model,
			String tno) {
		List<Teacher> teacherlist = teacherService.selectListBytno(tno);

		model.addAttribute("teacherList", teacherlist);
		return "admin/selectTeacher";
	}

	// 按照教师名查找教师
	@RequestMapping(value = "/selectTeacherByTname", method = RequestMethod.POST)
	public String selectTeacherByTname(HttpSession session, Model model,
			String tname) {
		List<Teacher> teacherlist = teacherService.selectListByname(tname);
		
		model.addAttribute("teacherList", teacherlist);
		return "admin/selectTeacher";
	}

	// 删除教师
	@RequestMapping(value = "/deleteTeacher", method = RequestMethod.GET)
	public String deleteTeacher(HttpSession session, Model model, String tid) {
		int tidInt = Integer.parseInt(tid);
		teacherService.deleteTeacher(tidInt);
		model.addAttribute("message", "成功删除教师");
		return "admin/selectTeacher";
	}

	// 跳转到修改教师页面
	@RequestMapping(value = "/updateTeacher", method = RequestMethod.GET)
	public String updateTeacher(HttpSession session, Model model, String tno) {
		Teacher t = teacherService.selectBytno(tno);
		model.addAttribute("teacher", t);
		return "admin/updateTeacher";
	}

	// 修改教师信息
	@RequestMapping(value = "/UpdateTeacher", method = RequestMethod.POST)
	public String UpdateTeacher(HttpSession session, Model model, String tno,
			String tname) {
		teacherService.updateTeacher(tno, tname);
		model.addAttribute("message", "成功修改信息");
		return "admin/updateTeacher";
	}

	// 重置教师密码
	@RequestMapping(value = "/resetTeacherPwd", method = RequestMethod.POST)
	public void resetTeacherPwd(HttpSession session, Model model, String tno,
			HttpServletRequest req, HttpServletResponse res) {
		String tpassword = MD5Util.MD5("000000");
		teacherService.updatePassword(tno, tpassword);

		try {
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
			PrintWriter out = res.getWriter();
			out.print("成功修改密码");
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 跳转到添加课程页面
	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public String addCourse(HttpSession session, Model model) {
		List<Teacher> teacherList = teacherService.getAll();

		model.addAttribute("list", teacherList);
		return "admin/addCourse";
	}

	// 添加课程
	@RequestMapping(value = "/AddCourse", method = RequestMethod.POST)
	public String AddCourse(HttpSession session, Model model, String cno,
			String cname, @PathVariable MultipartFile cimage, String tno,
			HttpServletRequest request) {
		String image = cimage.getOriginalFilename();
		Course c = new Course(cno, cname, image, tno);

		String realpath = request.getSession().getServletContext()
				.getRealPath("/img/course")
				;
		System.out.println(realpath);
		File file = new File(realpath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("文件夹不存在，已创建");
			file.mkdirs();
		} else {
			System.out.println("文件夹存在");
		}
		
		String imgUrl=realpath+cimage.getOriginalFilename();
		File f = new File(imgUrl);
		try {
			FileUtils.copyInputStreamToFile(cimage.getInputStream(), f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		courseService.addCourse(c);

		model.addAttribute("message", "成功添加课程");
		return "admin/addCourse";
	}

	// 跳转到查找课程页面
	@RequestMapping(value = "/selectCourse", method = RequestMethod.GET)
	public String selectCourse(HttpSession session, Model model) {

		return "admin/selectCourse";
	}

	// 按照课程号查找
	@RequestMapping(value = "/selectCourseBycno", method = RequestMethod.POST)
	public String selectCourseBycno(HttpSession session, Model model, String cno) {
		List<Course> list = courseService.selectListBycno(cno);

		model.addAttribute("list", list);
		return "admin/selectCourse";
	}

	// 按照课程名查找
	@RequestMapping(value = "/selectCourseByname", method = RequestMethod.POST)
	public String selectCourseByname(HttpSession session, Model model,
			String cname) {

		List<Course> list = courseService.selectListByname(cname);

		model.addAttribute("list", list);
		return "admin/selectCourse";
	}

	// 删除课程
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public String deleteCourse(HttpSession session, Model model, String cid) {
		int cidInt = Integer.parseInt(cid);
		courseService.deleteCourse(cidInt);

		model.addAttribute("message", "成功删除课程");
		return "admin/selectCourse";
	}

	// 跳转到修改课程页面
	@RequestMapping(value = "/updateCourse", method = RequestMethod.GET)
	public String updateCourse(HttpSession session, Model model, String cno) {
		Course c = courseService.selectBycno(cno);
		List<Teacher> teacherList = teacherService.getAll();

		model.addAttribute("list", teacherList);
		model.addAttribute("course", c);
		return "admin/updateCourse";
	}

	// 修改课程
	@RequestMapping(value = "/UpdateCourse", method = RequestMethod.POST)
	public String UpdateCourse(@PathVariable MultipartFile cimage,
			HttpSession session, Model model, String cno, String cname,
			String tno, HttpServletRequest request) {
		
		String image = cimage.getOriginalFilename();
		Course c = new Course(cno, cname, image, tno);

		
		
		String realpath = request.getSession().getServletContext()
				.getRealPath("/img/course");
		System.out.println(realpath);
		File file = new File(realpath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//文件夹不存在，已创建");
			file.mkdirs();
		} else {
			System.out.println("文件夹存在");
		}
		File f = new File(realpath + "/" + cimage.getOriginalFilename());
		try {
			FileUtils.copyInputStreamToFile(cimage.getInputStream(), f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		courseService.updateCourse(c);

		model.addAttribute("message", "成功修改课程");
		return "admin/updateCourse";
	}

	// 跳转到管理学生作业页面
	@RequestMapping(value = "/manageHomework", method = RequestMethod.GET)
	public String manageHomework(HttpSession session, Model model) {

		return "admin/manageHomework";
	}

	// 按照学号查询学生作业
	@RequestMapping(value = "/ManageHomework", method = RequestMethod.POST)
	public String ManageHomework(HttpSession session, Model model, String sno) {
		List<Sc> list = scService.getAllBySno(sno);

		model.addAttribute("list", list);
		return "admin/manageHomework";
	}

	// 删除学生作业
	@RequestMapping(value = "/deleteHomework", method = RequestMethod.GET)
	public String deleteHomework(HttpSession session, Model model,
			HttpServletRequest req, String sc_id, String sno) {
		int sc_idInt = Integer.parseInt(sc_id);
		Sc sc = scService.selectByscid(sc_idInt);
		String realpath = req.getSession().getServletContext()
				.getRealPath("/resource/upload")
				+ sc.getCno() + "/" + sc.getSno();
		File f = new File(realpath + "/" + sc.getScfilename());
		if (f.exists()) {
			f.delete();
		}

		scService.deleteByScid(sc_idInt);
		List<Sc> list = scService.getAllBySno(sno);

		model.addAttribute("list", list);
		return "admin/manageHomework";
	}

	// 跳转到设置课程界面
	@RequestMapping(value = "/setCourse", method = RequestMethod.GET)
	public String setCourse(HttpSession session, Model model) {
		List<ScData> list = scDataService.getAll();
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<Course> CnoList = courseService.getAllCno();
		
		model.addAttribute("CnoList", CnoList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		model.addAttribute("list", list);
		
		return "admin/setCourse";
	}

	// 删除设置的课程专业关系
	@RequestMapping(value = "/deleteSetCourse", method = RequestMethod.GET)
	public String deleteSetCourse(HttpSession session, Model model, String id) {
		int idInt = Integer.parseInt(id);
		scDataService.deleteByid(idInt);

		List<ScData> list = scDataService.getAll();
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		List<Course> CnoList = courseService.getAllCno();

		model.addAttribute("CnoList", CnoList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		model.addAttribute("list", list);
		return "admin/setCourse";
	}

	// 设置新的课程专业关系
	@RequestMapping(value = "/addSetCourse", method = RequestMethod.POST)
	public String addSetCourse(HttpSession session, Model model, String smajor,
			String sgrade,String sclass, String cno) {
		ScData sc = new ScData(cno, smajor, sgrade,sclass);
		Course c=new Course(cno,smajor,sgrade);
		
		scDataService.addSetCourse(sc);
		courseService.addSetCourse(c);
		
		List<ScData> list = scDataService.getAll();
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		List<Course> CnoList = courseService.getAllCno();

		model.addAttribute("CnoList", CnoList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		model.addAttribute("list", list);
		return "admin/setCourse";
	}

	// 添加新的专业年级班级
	@RequestMapping(value = "/addMajorGradeClass", method = RequestMethod.POST)
	public String addMajorGradeClass(HttpSession session, Model model,
			String smajor, String sclass, String sgrade) {
		ScData sc = new ScData();
		sc.setSmajor(smajor);
		sc.setSgrade(sgrade);
		sc.setSclass(sclass);

		scDataService.addMajorGradeClass(sc);

		List<ScData> list = scDataService.getAll();
		List<ScData> majorList = scDataService.getMajorDistinct();
		List<ScData> gradeList = scDataService.getGradeDistinct();
		List<ScData> classList = scDataService.getClassDistinct();
		List<Course> CnoList = courseService.getAllCno();

		model.addAttribute("CnoList", CnoList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		model.addAttribute("list", list);
		return "admin/setCourse";
	}

	// 跳转到管理学生作业页面
	@RequestMapping(value = "/manageAdvice", method = RequestMethod.GET)
	public String manageAdvice(HttpSession session, Model model) {

		return "admin/manageAdvice";
	}

	// 跳转到管理学生作业页面
	@RequestMapping(value = "/manageError", method = RequestMethod.GET)
	public String manageError(HttpSession session, Model model) {

		return "admin/manageError";
	}

}
