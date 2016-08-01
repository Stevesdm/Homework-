package com.spring.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.UseException;
import com.spring.model.Course;
import com.spring.model.Sc;
import com.spring.model.ScData;
import com.spring.model.Student;
import com.spring.model.Task;
import com.spring.model.Teacher;
import com.spring.service.CourseService;
import com.spring.service.ScDataService;
import com.spring.service.ScService;
import com.spring.service.StudentService;
import com.spring.service.TaskService;
import com.spring.service.TeacherService;
import com.spring.util.FileDownload;
import com.spring.util.MD5Util;
import com.spring.util.PageRoll;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

	@Resource
	private TeacherService teacherService = null;

	@Resource
	private CourseService courseService = null;

	@Resource
	private StudentService studentService = null;

	@Resource
	private TaskService taskService = null;

	@Resource
	private ScService scService = null;

	@Resource
	private ScDataService scDataService = null;

	// 跳转到设置作业界面
	@RequestMapping(value = "/homepage")
	public String homepage() {
		return "teacher/mainpage";
	}

	// 跳转到修改密码
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	public String changePwd(HttpSession session) {
		return "teacher/changePassword";
	}

	// 修改密码
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public String changePassword(HttpSession session, HttpServletRequest req,
			Model model) {
		String newPassword = ((String) req.getParameter("password1")).trim();
		String newPasswordAgain = ((String) req.getParameter("password2"))
				.trim();

		System.out.println(newPassword);
		Teacher t = (Teacher) session.getAttribute("user");
		String tno = t.getTno();

		if (!newPassword.equals(newPasswordAgain) || newPassword.length() < 6
				|| newPassword.length() > 15) {
			model.addAttribute("message", "新密码信息填写错误");
			return "teacher/changePassword";
		} else {
			teacherService.updatePassword(tno, MD5Util.MD5(newPasswordAgain));
			model.addAttribute("message", "密码修改成功");
			return "teacher/changePassword";
		}
	}

	// 课程列表
	@RequestMapping(value = "/courseList", method = RequestMethod.GET)
	public String CourseList(HttpSession session, Model model) {
		Teacher t = (Teacher) session.getAttribute("user");
		String tno = t.getTno();
		List<Course> courseList = courseService.getCourseByTno(tno);
		model.addAttribute("courseList", courseList);
		return "teacher/courseList";
	}

	// 跳转到查看作业页面
	@RequestMapping(value = "/lookHomework/{cno}", method = RequestMethod.GET)
	public String upload(@PathVariable String cno, HttpSession session,
			Model model, HttpServletRequest req) {
		Teacher t = (Teacher) session.getAttribute("user");
		Course course = courseService.selectBycno(cno);
		model.addAttribute("course", course);

		// List<String> sclasslist = studentService.getClassByMajorGrade(cmajor,
		// cgrade);
		List<ScData> sclasslist = scDataService.getCLassByCno(cno);

		List<Task> tasklist = taskService.getAll(cno);

		model.addAttribute("sclasslist", sclasslist);
		model.addAttribute("tasklist", tasklist);

		return "teacher/lookHomework";
	}

	// 跳转到查看作业页面---按照班级查询
	@RequestMapping(value = "/lookHomeworkByClass/{cno}")
	public String lookHomeworkByClass(@PathVariable String cno,
			HttpSession session, Model model, HttpServletRequest req) {
		Teacher t = (Teacher) session.getAttribute("user");
		Course course = courseService.selectBycno(cno);
		String sctimes = req.getParameter("sctimes");
		String MajorAndClass = req.getParameter("sclass");

		String smajor = MajorAndClass.substring(0, MajorAndClass.indexOf("."));
		String sclass = MajorAndClass.substring(MajorAndClass.indexOf(".") + 1);
		// 分页
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		int sctimesInt = Integer.parseInt(sctimes);

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("smajor", smajor);
		map1.put("sclass", sclass);
		map1.put("sctimes", sctimesInt);
		map1.put("cno", cno);

		int totalCount = scService.totalBysclasstimes(map1);
		pageroll.setTotalCount(totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smajor", smajor);
		map.put("sclass", sclass);
		map.put("sctimes", sctimesInt);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsclasstimesByPage(map);

		List<ScData> sclasslist = scDataService.getCLassByCno(cno);
		List<Task> tasklist = taskService.getAll(cno);

		model.addAttribute("smajor", smajor);
		model.addAttribute("sclass", sclass);
		model.addAttribute("sctimes", sctimes);
		model.addAttribute("sclasslist", sclasslist);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("course", course);
		model.addAttribute("sclistbypage", sclistbypage);
		model.addAttribute("pageroll", pageroll);
		return "teacher/lookHomeworkByClass";
	}

	// 跳转到查看作业页面---按照学号查询
	@RequestMapping(value = "/lookHomeworkByNo/{cno}")
	public String lookHomeworkByNo(@PathVariable String cno,
			HttpSession session, Model model, HttpServletRequest req) {
		Course course = courseService.selectBycno(cno);

		List<ScData> sclasslist = scDataService.getCLassByCno(cno);
		List<Task> tasklist = taskService.getAll(cno);

		// 分页
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		String sno = req.getParameter("sno");

		int TotalCount = scService.Total(sno, cno);
		pageroll.setTotalCount(TotalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsnocnoByPage(map);

		model.addAttribute("sclasslist", sclasslist);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("course", course);
		model.addAttribute("sclistbypage", sclistbypage);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("sno", sno);
		return "teacher/lookHomeworkByNo";
	}

	// 跳转到查看作业页面---按照姓名查询
	@RequestMapping(value = "/lookHomeworkByName/{cno}")
	public String lookHomeworkByName(@PathVariable String cno,
			HttpSession session, Model model, HttpServletRequest req) {
		Course course = courseService.selectBycno(cno);

		List<ScData> sclasslist = scDataService.getCLassByCno(cno);
		List<Task> tasklist = taskService.getAll(cno);

		// 分页
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		String sname = req.getParameter("sname");

		int TotalCount = scService.TotalByName(sname, cno);
		pageroll.setTotalCount(TotalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sname", sname);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Sc> sclistbypage = scService.getscFromsnamecnoByPage(map);

		model.addAttribute("sclasslist", sclasslist);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("course", course);
		model.addAttribute("sclistbypage", sclistbypage);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("sname", sname);

		return "teacher/lookHomeworkByName";
	}

	// 跳转到查看作业页面---按班级未交作业查询
	@RequestMapping(value = "/lookHomeworkUnupload/{cno}")
	public String lookHomeworkUnupload(@PathVariable String cno,
			HttpSession session, Model model, HttpServletRequest req) {
		Course course = courseService.selectBycno(cno);

		String sctimes = req.getParameter("sctimes");
		String MajorAndClass = req.getParameter("sclass");

		String smajor = MajorAndClass.substring(0, MajorAndClass.indexOf("."));
		String sclass = MajorAndClass.substring(MajorAndClass.indexOf(".") + 1);

		int sctimesInt = Integer.parseInt(sctimes);

		List<ScData> sclasslist = scDataService.getCLassByCno(cno);
		List<Task> tasklist = taskService.getAll(cno);
		// 分页
		PageRoll pageroll = new PageRoll();

		String currPage = req.getParameter("currPage");
		if (currPage != null) {
			pageroll.setCurrPage(Integer.parseInt(currPage));
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("smajor", smajor);
		map1.put("sclass", sclass);
		map1.put("sctimes", sctimesInt);
		map1.put("cno", cno);

		int TotalCount = studentService.selectUnupload(map1);
		pageroll.setTotalCount(TotalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smajor", smajor);
		map.put("sclass", sclass);
		map.put("sctimes", sctimesInt);
		map.put("cno", cno);
		map.put("startRow",
				(pageroll.getCurrPage() - 1) * pageroll.getPageSize());
		map.put("pageSize", pageroll.getPageSize());

		List<Student> stulist = studentService.selectUnuploadByPage(map);

		model.addAttribute("sclasslist", sclasslist);
		model.addAttribute("tasklist", tasklist);
		model.addAttribute("stulist", stulist);
		model.addAttribute("pageroll", pageroll);
		model.addAttribute("course", course);
		model.addAttribute("sclass", sclass);
		model.addAttribute("sctimes", sctimes);

		return "teacher/lookHomeworkByUnupload";
	}

	// 跳转到设置作业界面
	@RequestMapping(value = "/setTask/{cno}")
	public String setTask(@PathVariable String cno, HttpSession session,
			Model model, HttpServletRequest req) {
		Course course = courseService.selectBycno(cno);
		List<Task> tasks = taskService.getAll(cno);

		model.addAttribute("course", course);
		model.addAttribute("tasks", tasks);
		return "teacher/setTask";
	}

	// 添加作业
	@RequestMapping(value = "/addTask/{cno}")
	public String addTask(@PathVariable String cno,
			@PathVariable MultipartFile homeworkFile, HttpSession session,
			Model model, HttpServletRequest req) throws IOException,
			UseException {
		String task_title = req.getParameter("task_title");
		String task_stoptime = req.getParameter("task_stoptime");
		String task_content = req.getParameter("task_content");

		String message;

		String sctimes = taskService.selectsctimeMax(cno);

		if (task_title.length() > 20 || task_title.length() == 0) {
			message = "标题不能为空且长度不能超过20";
			model.addAttribute("message", message);
			return "redirect:/teacher/setTask/{cno}";
		} else if (task_stoptime.length() == 0) {
			message = "时间不能为空";
			model.addAttribute("message", message);
			return "redirect:/teacher/setTask/{cno}";
		} else if (task_content.length() == 0 || task_content.length() > 2000) {
			message = "内容不能为空且长度不能超过2000";
			model.addAttribute("message", message);
			return "redirect:/teacher/setTask/{cno}";
		} else if (homeworkFile == null) {
			int sctimesInt;
			if (sctimes == null) {
				sctimesInt = 1;
			} else {
				sctimesInt = Integer.parseInt(sctimes);
				sctimesInt = sctimesInt + 1;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cno", cno);
			map.put("sctimesInt", sctimesInt);
			map.put("task_title", task_title);
			map.put("task_stoptime", task_stoptime);
			map.put("task_content", task_content);

			int flag = taskService.insertAll(map);
			if (flag != 0) {
				message = "添加成功";
			} else {
				message = "添加失败";
			}
			return "redirect:/teacher/setTask/{cno}";
		} else {

			if (homeworkFile.getOriginalFilename() == "") {
				System.out.println("文件无效！！！");
				throw new UseException("文件不能为空");
			}
			// 获取作业上传路径
			String realpath = req.getSession().getServletContext()
					.getRealPath("/resource/taskFile")
					+ cno;
			File file = new File(realpath);
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//文件夹不存在，已创建");
				file.mkdirs();
			} else {
				System.out.println("文件夹存在");
			}

			File f = new File(realpath + "/"
					+ homeworkFile.getOriginalFilename());
			FileUtils.copyInputStreamToFile(homeworkFile.getInputStream(), f);

			int sctimesInt;
			if (sctimes == null) {
				sctimesInt = 1;
			} else {
				sctimesInt = Integer.parseInt(sctimes);
				sctimesInt = sctimesInt + 1;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cno", cno);
			map.put("sctimesInt", sctimesInt);
			map.put("task_title", task_title);
			map.put("task_stoptime", task_stoptime);
			map.put("task_content", task_content);
			map.put("task_file", homeworkFile.getOriginalFilename());
			int flag = taskService.insertAllFile(map);
			if (flag != 0) {
				message = "添加成功";
			} else {
				message = "添加失败";
			}
			return "redirect:/teacher/setTask/{cno}";
		}
	}

	// 修改作业
	@RequestMapping(value = "/updateTask/{cno}")
	public String updateTask(@PathVariable String cno,
			@PathVariable MultipartFile homeworkFile, HttpSession session,
			Model model, HttpServletRequest req) throws IOException,
			UseException {
		String task_title = req.getParameter("task_title");
		String task_stoptime = req.getParameter("task_stoptime");
		String task_content = req.getParameter("task_content");
		String task_id = req.getParameter("task_id");
		int task_id_int = Integer.parseInt(task_id);
		String message;

		if (task_title.length() > 20 || task_title.length() == 0) {
			message = "标题不能为空且长度不能超过20";
			model.addAttribute("message", message);
			return "redirect:/teacher/setTask/{cno}";
		} else if (task_stoptime.length() == 0) {
			message = "时间不能为空";
			model.addAttribute("message", message);
			return "redirect:/teacher/setTask/{cno}";
		} else if (task_content.length() == 0 || task_content.length() > 2000) {
			message = "内容不能为空且长度不能超过2000";
			model.addAttribute("message", message);
			return "redirect:/teacher/setTask/{cno}";
		} else if (homeworkFile == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("task_title", task_title);
			map.put("task_stoptime", task_stoptime);
			map.put("task_content", task_content);
			map.put("task_id", task_id_int);

			int flag = taskService.updateBykey(map);
			if (flag != 0) {
				message = "更新成功";
			} else {
				message = "更新失败";
			}
			return "redirect:/teacher/setTask/{cno}";
		} else {

			if (homeworkFile.getOriginalFilename() == "") {
				System.out.println("文件无效！！！");
				throw new UseException("文件不能为空");
			}
			// 获取作业上传路径
			String realpath = req.getSession().getServletContext()
					.getRealPath("/resource/taskFile")
					+ cno;
			File file = new File(realpath);
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//文件夹不存在，已创建");
				file.mkdirs();
			} else {
				System.out.println("文件夹存在");
			}
			File f = new File(realpath + "/"
					+ homeworkFile.getOriginalFilename());
			FileUtils.copyInputStreamToFile(homeworkFile.getInputStream(), f);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("task_title", task_title);
			map.put("task_stoptime", task_stoptime);
			map.put("task_content", task_content);
			map.put("task_file", homeworkFile.getOriginalFilename());
			map.put("task_id", task_id_int);

			int flag = taskService.updateFileBykey(map);
			if (flag != 0) {
				message = "更新成功";
			} else {
				message = "更新失败";
			}
			return "redirect:/teacher/setTask/{cno}";
		}
	}

	// task_file文件的下载
	@RequestMapping(value = "/download/{cno}", method = RequestMethod.GET)
	public String downloadTask(@PathVariable String cno, String taskfilename,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		System.out.println("文件" + taskfilename + "选中下载");
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/resource/taskFile")
				+ cno;
		String downLoadPath = ctxPath + "/" + taskfilename;
		System.out.println("当前路径" + downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(taskfilename.getBytes("utf-8"), "ISO8859-1"));
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

	// 作业文件单个下载
	@RequestMapping(value = "/{sno}/download", method = RequestMethod.GET)
	public String download(@PathVariable String sno, String scfilename,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String sc_id = request.getParameter("sc_id");
		int Sc_Id = Integer.parseInt(sc_id);
		Sc sc = scService.selectByscid(Sc_Id);
		String cno = sc.getCno();

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

			scService.downloaded(Sc_Id);

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

	// 作业文件批量下载
	@RequestMapping(value = "downloadAll", method = RequestMethod.POST)
	public void downloadAll(HttpServletRequest req, HttpServletResponse res) {
		String[] fileSelected = req.getParameterValues("fileSelected");
		List<File> fileList = new ArrayList<File>();

		String tempPath = req.getSession().getServletContext()
				.getRealPath("/resource/upload");

		for (int i = 0; i < fileSelected.length; i++) {
			int sc_id = Integer.parseInt(fileSelected[i]);
			Sc sc = scService.selectByscid(sc_id);

			scService.downloaded(sc_id);

			File f = new File(tempPath + sc.getCno() + "/" + sc.getSno() + "/"
					+ sc.getScfilename());
			fileList.add(f);
		}

		try {
			FileDownload.downLoadFiles(fileList, req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下载本班本次全部作业
	@RequestMapping(value = "downloadAllByclass", method = RequestMethod.GET)
	public void downloadAllByclass(HttpServletRequest req,
			HttpServletResponse res) {
		String smajor = req.getParameter("major");
		String sclass = req.getParameter("class");
		String sctimes = req.getParameter("times");
		String cno = req.getParameter("cno");
		int sctimesInt = Integer.parseInt(sctimes);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smajor", smajor);
		map.put("sclass", sclass);
		map.put("sctimes", sctimesInt);
		map.put("cno", cno);
		List<Sc> list = scService.getScByMajorClasTimes(map);

		List<File> fileList = new ArrayList<File>();

		String tempPath = req.getSession().getServletContext()
				.getRealPath("/resource/upload");

		for (int i = 0; i < list.size(); i++) {
			int sc_id = list.get(i).getSc_id();

			Sc sc = scService.selectByscid(sc_id);

			scService.downloaded(sc_id);

			File f = new File(tempPath + sc.getCno() + "/" + sc.getSno() + "/"
					+ sc.getScfilename());
			fileList.add(f);
		}
		try {
			FileDownload.downLoadFiles(fileList, req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 下载本班本次未下载过的作业
	@RequestMapping(value = "downloadUnByclass", method = RequestMethod.GET)
	public void downloadUnByclass(HttpServletRequest req,
			HttpServletResponse res) {
		String smajor = req.getParameter("major");
		String sclass = req.getParameter("class");
		String sctimes = req.getParameter("times");
		String cno = req.getParameter("cno");
		int sctimesInt = Integer.parseInt(sctimes);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smajor", smajor);
		map.put("sclass", sclass);
		map.put("sctimes", sctimesInt);
		map.put("cno", cno);
		List<Sc> list = scService.getScUnByMajorClasTimes(map);

		List<File> fileList = new ArrayList<File>();

		String tempPath = req.getSession().getServletContext()
				.getRealPath("/resource/upload");
		for (int i = 0; i < list.size(); i++) {
			int sc_id = list.get(i).getSc_id();

			Sc sc = scService.selectByscid(sc_id);

			scService.downloaded(sc_id);

			File f = new File(tempPath + sc.getCno() + "/" + sc.getSno() + "/"
					+ sc.getScfilename());
			fileList.add(f);
		}
		try {
			FileDownload.downLoadFiles(fileList, req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
