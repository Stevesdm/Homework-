package com.spring.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.util.FileDownload;

@Controller
@RequestMapping(value = "/temp")
public class TempController {

	@RequestMapping(value = "/remark", method = RequestMethod.GET)
	public String CourseList(HttpSession session) {
		return "student/showComment";
	}

	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public String setTask(HttpSession session) {
		return "teacher/setTask";
	}

	@RequestMapping(value = "/look", method = RequestMethod.GET)
	public String look(HttpSession session) {
		return "teacher/lookHomework";
	}

}
