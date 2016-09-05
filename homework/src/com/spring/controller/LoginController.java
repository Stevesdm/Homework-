package com.spring.controller;

import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.spring.dao.UseException;
import com.spring.model.Admin;
import com.spring.model.Student;
import com.spring.model.Teacher;
import com.spring.service.AdminService;
import com.spring.service.StudentService;
import com.spring.service.TeacherService;
import com.spring.util.MD5Util;

@Controller
public class LoginController {

	@Resource
	private StudentService studentService = null;
	@Resource
	private TeacherService teacherService = null;
	@Resource
	private AdminService adminService = null;

	/*
	 * 用户登录学生学号9个字节，老师5个字节，用于区分不不同的用户 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userno, String password, String verifyCode,
			HttpSession session, HttpServletRequest request)
			throws UseException {
		String code = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
		if (verifyCode.equals(code)) {
			switch (userno.trim().length()) {
			case 9:
				
				System.out.println("学生登录");
				
				List<Student> stulist = studentService.getAllStudents();
				for (Student s : stulist) {
					if (s.getSno().equals(userno.trim())
							&& s.getSpassword().trim().equals(MD5Util.MD5(password.trim()))) {
						Student student = studentService.selectBysno(userno);
						session.setAttribute("user", student);
						System.out.println("登陆成功...");
						return "student/mainpage";
					}
				}
				throw new UseException("用户名或密码不正确");

			case 5:
				System.out.println("教师登陆");
				List<Teacher> tealist = teacherService.getAll();
				for (Teacher t : tealist) {
					if (t.getTno().equals(userno.trim())
							&& t.getTpassword().equals(MD5Util.MD5(password.trim()))) {
						Teacher teacher = teacherService.selectBytno(userno);
						session.setAttribute("user", teacher);
						System.out.println("登陆成功...");
						return "teacher/mainpage";
					}
				}
				throw new UseException("用户名或密码不正确！！！");
			case 7:
				System.out.println("管理员登陆");
				List<Admin> adminlist = adminService.getAll();
				for (Admin t : adminlist) {
					if (t.getAno().equals(userno.trim())
							&& t.getApassword().equals(MD5Util.MD5(password.trim()))) {
						Admin admin = adminService.selectByAno(userno);
						session.setAttribute("user", admin);
						System.out.println(admin.getAname()+"登陆成功...");
						return "admin/mainpage";
					}
				}
				throw new UseException("用户名或密码不正确");
			default:
				throw new UseException("用户名或密码不正确");
			}
		} else {
			throw new UseException("验证码错误");
		}
	}

	/*
	 * 注销登录
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request) {
		Enumeration<String> em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		request.getSession().invalidate();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return "redirect:" + basePath;
	}
}
