package com.yihoyoung.game.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yihoyoung.game.dao.UserDao;
import com.yihoyoung.game.exception.ServiceException;

@Controller
public class AuthorizeController {
	
	@Autowired
	private UserDao userDao;

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorizeController.class);

	@RequestMapping(value = "/checkid", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView checkid(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, Locale locale, Model model) {
		model.addAttribute("result", 0);
		String userId = httpRequest.getParameter("userId");
		logger.info(userId);
		if (userDao.isExistID(userId)) {
			model.addAttribute("id", userId);
			model.addAttribute("exsit", 1);
			return new ModelAndView("jsonView");
		} else {
			model.addAttribute("id", userId);
			model.addAttribute("exsit", 0);
			logger.info("id[" + userId + "] not exists.");
			return new ModelAndView("jsonView");
		}
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ModelAndView login(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, Locale locale, Model model) {
		String user_id = httpRequest.getParameter("user_id");
		String password = httpRequest.getParameter("password");
		Map<String, Object> userInfo = userDao.login(user_id, password);

		if (userInfo == null) {
			throw new ServiceException("user info is not exist.");
		}
		
		if(!StringUtils.equals(password, MapUtils.getString(userInfo, "password"))) {
			throw new ServiceException("password is wrong.");
		}

		model.addAttribute("result", 0);
		return new ModelAndView("jsonView");
	}
}
