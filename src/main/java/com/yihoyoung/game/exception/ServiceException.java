package com.yihoyoung.game.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceException extends RuntimeException {
	public ServiceException() {
		super();
	}

	private static final long serialVersionUID = -5741677189250808988L;

	private String detailMessage;

	public ServiceException(String messageCode) {
		this(messageCode, (String) null);
	}

	public ServiceException(String messageCode, Throwable cause) {
		this(messageCode, null, cause);
	}

	public ServiceException(String messageCode, String detailMessage) {
		super(messageCode);
		this.detailMessage = detailMessage;
	}

	public ServiceException(String messageCode, String detailMessage,
			Throwable cause) {
		super(messageCode, cause);
		this.detailMessage = detailMessage;
	}

	public String getMessageCode() {
		return super.getMessage();
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	@Override
	public String getMessage() {
		String detailMessage = getDetailMessage();
		if (StringUtils.isEmpty(detailMessage)) {
			return getMessageCode();
		} else {
			return "[" + getMessageCode() + "] " + detailMessage;
		}
	}
}