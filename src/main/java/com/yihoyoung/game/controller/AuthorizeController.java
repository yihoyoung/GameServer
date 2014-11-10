package com.yihoyoung.game.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class AuthorizeController {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);
	
	@RequestMapping(value = "/checkid", method = {RequestMethod.POST})
	public ModelAndView checkid(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Locale locale,  Model model) {
		model.addAttribute("result", 0);
		logger.info(" 공통 로그 체크 ");
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ModelAndView login(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Locale locale,  Model model) {
		String login_id = httpRequest.getParameter("login_id");
		String login_pwd = httpRequest.getParameter("login_pwd");
		model.addAttribute("result", 0);
		return new ModelAndView("jsonView");
	}
}
