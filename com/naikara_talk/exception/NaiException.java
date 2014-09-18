package com.naikara_talk.exception;

public class NaiException extends Exception {

	private String msg;

	public NaiException() {
		super();
	}

	public NaiException(Exception e) {
		super(e);
	}

	public NaiException(String msg) {
		super();
		setMsg(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
