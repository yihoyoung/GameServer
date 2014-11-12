package com.yihoyoung.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yihoyoung.game.dao.LogDao;

@Controller
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	final static Logger logger = LoggerFactory
			.getLogger(AuthorizeInterceptor.class);
	
	@Autowired
	private LogDao logDaoInstance;

	public AuthorizeInterceptor() {
		logger.info("###### constructor ###### AuthorizeInterceptor==>" + this);
	}

}
