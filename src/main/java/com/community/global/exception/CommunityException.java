package com.community.global.exception;

import com.community.global.enums.ErrorCode;

public class CommunityException extends RuntimeException {
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

	public CommunityException(Exception e) {
		super(e.getMessage());
		this.errorCode = null;
		this.message = e.getMessage();
	}
}
