package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/about")
public class AboutController {

	// 回到关于我们
	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String homePage() {
		return "about/aboutUs";
	}

	// 回到意见反馈
	@RequestMapping(value = "/adviceFeed", method = RequestMethod.GET)
	public String adviceFeed() {
		return "about/adviceFeed";
	}

	// 回到联系我们
	@RequestMapping(value = "/contractUs", method = RequestMethod.GET)
	public String contractUs() {
		return "about/contractUs";
	}

	// 回到团队介绍
	@RequestMapping(value = "/groupIntroduce", method = RequestMethod.GET)
	public String groupIntroduce() {
		return "about/groupIntroduce";
	}

	// 回到友情链接
	@RequestMapping(value = "/links", method = RequestMethod.GET)
	public String links() {
		return "about/links";
	}

	// 回到人才招聘
	@RequestMapping(value = "/peopleWanted", method = RequestMethod.GET)
	public String peopleWanted() {
		return "about/peopleWanted";
	}
}
