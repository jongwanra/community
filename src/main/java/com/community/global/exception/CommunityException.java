package com.community.global.exception;

import org.springframework.context.ApplicationEventPublisher;

import com.community.global.enums.ErrorCode;

public class CommunityException extends RuntimeException{
	private ErrorCode errorCode;
	private String message;

	public CommunityException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.message = errorCode.getMessage();
	}

	public CommunityException(ErrorCode errorCode, Object... args) {
		super(errorCode.formatMessage(args));
		this.errorCode = errorCode;
		this.message = errorCode.formatMessage(args);
	}


}
