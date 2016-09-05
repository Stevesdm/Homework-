package com.spring.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.spring.dao.UseException;
import com.spring.model.Course;
import com.spring.model.Sc;
import com.spring.model.Student;
import com.spring.model.Task;
import com.spring.service.CourseService;
import com.spring.service.ScDataService;
import com.spring.service.ScService;
import com.spring.service.StudentService;
import com.spring.service.TaskService;
import com.spring.util.MD5Util;
import com.spring.util.PageRoll;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	@Resource
	private StudentService studentService = null;

	@Resource
	private CourseService courseService = null;

	@Resource
	private ScService scService = null;

	@Resource
	private ScDataService scdataService = null;

	@Resource
	private TaskService settaskService = null;

	/**
	 * 返回到学生主页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String returnhome(HttpSession session) {
		return "student/mainpage";
	}
	
	/**
	 * 跳转到修改密码的页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	public String changePwd(HttpSession session) {
		return "student/changePassword";
	}
	
	/**
	 * 跳转到学生课程列表页面，并显示该学生所学的所有课程
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
	public String CourseList(HttpSession session, Model model) {
		Student s = (Student) session.getAttribute("user");
		String grade = s.getSgrade();
		String major = s.getSmajor();
		List<Course> courseList = new ArrayList<Course>();
		List conList = scdataService.getStudentCourse(major, grade);
		Iterator it = conList.iterator();
		while (it.hasNext()) {
			String cno = (String) it.next();
			if (cno != null) {
				Course c = (Course) courseService.selectBycno(cno);
				courseList.add(c);
			}
		}
		model.addAttribute("courseList", courseList);
		return "student/courseList";
	}

	/**
	 * 跳转到课程资源页面，并显示出所有课程
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resCourseList", method = RequestMethod.GET)
	public String ResCourseList(HttpSession session, Model model) {
		Student s = (Student) session.getAttribute("user");
		String grade = s.getSgrade();
		String major = s.getSmajor();
		List<Course> courseList = new ArrayList<Course>();
		List conList = scdataService.getStudentCourse(major, grade);
		Iterator it = conList.iterator();
		while (it.hasNext()) {
			String cno = (String) it.next();
			if (cno != null) {
				Course c = (Course) courseService.selectBycno(cno);
				courseList.add(c);
			}
		}
		model.addAttribute("courseList", courseList);
		return "student/CourseRecList";
	}

	/**
	 * 跳转到上传作业页面，并查询出该课程作业内容，该学生之前上交的作业
	 * @param cno
	 * @param session
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/upload/{cno}", method = RequestMethod.GET)
	public String upload(@PathVariable String cno, HttpSession session,
			Model model, HttpServletRequest req) {
		Student s = (Student) session.getAttribute("user");
		String sno = s.getSno();
		List<Task> tasklist = settaskService.getAll(cno);
		Course course = courseService.selectBycno(cno);
		// 分页
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		int totalCount = scService.Total(sno, cno);// 总记录数
		pageroll.setTotalCount(totalCount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsnocnoByPage(map);

		model.addAttribute("course", course);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("sclistbypage", sclistbypage);
		return "student/upload";
	}

	/**
	 * 上传作业失败，将失败信息返回到上传作业页面
	 * @param cno
	 * @param session
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/uploadError/{cno}", method = RequestMethod.GET)
	public String uploaderror(@PathVariable String cno, HttpSession session,
			Model model, HttpServletRequest req) {
		Student s = (Student) session.getAttribute("user");
		String sno = s.getSno();
		List<Task> tasklist = settaskService.getAll(cno);
		Course course = courseService.selectBycno(cno);
		// 分页
		model.addAttribute("message", "上传超时");
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		int totalCount = scService.Total(sno, cno);// 总页数
		pageroll.setTotalCount(totalCount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsnocnoByPage(map);

		model.addAttribute("course", course);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("sclistbypage", sclistbypage);
		return "student/upload";
	}

	/**
	 * 成功上传作业，返回课程列表页面，并且提示上传成功
	 * @param cno
	 * @param session
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/uploadSuccess/{cno}", method = RequestMethod.GET)
	public String uploadsuccess(@PathVariable String cno, HttpSession session,
			Model model, HttpServletRequest req) {
		Student s = (Student) session.getAttribute("user");
		String sno = s.getSno();
		List<Task> tasklist = settaskService.getAll(cno);
		Course course = courseService.selectBycno(cno);
		// 分页
		model.addAttribute("message", "上传成功");
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		int totalCount = scService.Total(sno, cno);// 总记录数
		pageroll.setTotalCount(totalCount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsnocnoByPage(map);

		model.addAttribute("course", course);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("sclistbypage", sclistbypage);
		return "student/upload";
	}

	/**
	 * 成功更新已有的作业，返回到上传作业页面，并且提示
	 * @param cno
	 * @param session
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/uploadUpdateSuccess/{cno}", method = RequestMethod.GET)
	public String uploadUpdatesuccess(@PathVariable String cno,
			HttpSession session, Model model, HttpServletRequest req) {
		Student s = (Student) session.getAttribute("user");
		String sno = s.getSno();
		List<Task> tasklist = settaskService.getAll(cno);
		Course course = courseService.selectBycno(cno);

		model.addAttribute("message", "更新成功");
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		int totalCount = scService.Total(sno, cno);// 总记录数
		pageroll.setTotalCount(totalCount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsnocnoByPage(map);

		model.addAttribute("course", course);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("sclistbypage", sclistbypage);
		return "student/upload";
	}

	/**
	 * 更新作业判断逻辑代码，查看是否能正常更新作业
	 * @param cno
	 * @param attach
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws UseException
	 */
	@RequestMapping(value = "/{cno}/upfile", method = RequestMethod.POST)
	public String upfile(@PathVariable String cno,
			@PathVariable MultipartFile attach, HttpServletRequest request,
			Model model, HttpSession session) throws IOException, UseException {
		// 第几次作业
		String times = request.getParameter("times");
		System.out.println(cno + times);
		Integer sctimes = Integer.valueOf(times);

		Student s = (Student) session.getAttribute("user");
		String sno = s.getSno();
		String sname = s.getSname();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("sctimes", sctimes);
		Task task = settaskService.selectBycnosctimes(map);

		java.util.Date date = new java.util.Date();
		java.sql.Date date1 = new java.sql.Date(date.getTime());

		if (date1.getTime() > task.getStoptime().getTime()) {
			System.out.println("上传超时");
			return "redirect:/student/uploadError/{cno}";
		}

		if (attach.getOriginalFilename() == "") {
			System.out.println("文件无效！！！");
			throw new UseException("文件不能为空");
		}

		String realpath = request.getSession().getServletContext()
				.getRealPath("/resource/upload")
				+ cno + "/" + sno;
		System.out.println(realpath);

		File file = new File(realpath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//文件夹不存在，已创建");
			file.mkdirs();
		} else {
			System.out.println("文件夹存在");
		}

		StringBuffer sb = new StringBuffer();
		sb.append(sname);
		sb.append("第");
		sb.append(times);
		sb.append("次作业");

		// File f = new File(realpath + "/" + attach.getOriginalFilename());
		File f = new File(realpath + "/" + sb.toString());
		FileUtils.copyInputStreamToFile(attach.getInputStream(), f);

		// Sc sc = new Sc(sno, sname, cno, sctimes,
		// attach.getOriginalFilename(),
		// date1);
		Sc sc = new Sc(sno, sname, cno, sctimes, sb.toString(), date1);

		Map<String, Object> map2 = new HashMap<String, Object>();
		System.out.println(sno + "  " + cno + "  " + sctimes);

		map2.put("sno", sno);
		map2.put("cno", cno);
		map2.put("sctimes", sctimes);
		String record = scService.selectAllbykey(map2);

		if (record != null) {
			scService.updatebykey(sc);
			// System.out.println("作业更新成功");
			return "redirect:/student/uploadUpdateSuccess/{cno}";
		} else {
			scService.insertSelective(sc);
			// System.out.println("作业上交成功");
			return "redirect:/student/uploadSuccess/{cno}";
		}
	}

	/**
	 * 上传作业页面ajax，根据作业次数，获取该次作业的全部内容
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/getHomework", method = RequestMethod.POST)
	public void getHomework(HttpServletRequest req, HttpServletResponse res) {
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		res.setContentType("application/json;charset=utf-8");

		Integer sctimes = Integer.parseInt(req.getParameter("sctimes"));
		String cno = req.getParameter("cno");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("sctimes", sctimes);
		Task task = settaskService.selectBycnosctimes(map);
		Gson gson = new Gson();
		String json = gson.toJson(task);

		System.out.println(json);

		try {
			PrintWriter out = res.getWriter();
			out.print(json.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 学生单个下载作业
	 * @param sno
	 * @param scfilename
	 * @param cno
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{sno}/download", method = RequestMethod.GET)
	public String download(@PathVariable String sno, String scfilename,
			String cno, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		System.out.println("文件" + scfilename + "选中下载");
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/resource/upload")
				+ cno + "/" + sno;
		String downLoadPath = ctxPath + "/" + scfilename;
		System.out.println("当前路径" + downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(scfilename.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.addAttribute("bos", bos);
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	/**
	 * 修改密码逻辑实现
	 * @param session
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public String changePassword(HttpSession session, HttpServletRequest req,
			Model model) {
		String newPassword = ((String) req.getParameter("password1")).trim();
		String newPasswordAgain = ((String) req.getParameter("password2"))
				.trim();

		System.out.println(newPassword);
		Student s = (Student) session.getAttribute("user");
		String sno = s.getSno();

		if (!newPassword.equals(newPasswordAgain) || newPassword.length() < 6
				|| newPassword.length() > 15) {
			model.addAttribute("message", "新密码信息填写错误");
			return "student/changePassword";
		} else {
			studentService.updatePassword(sno, MD5Util.MD5(newPasswordAgain));
			model.addAttribute("message", "密码修改成功");
			return "student/changePassword";
		}
	}

}
