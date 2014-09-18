package com.naikara_talk.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloSample extends ActionSupport {

    private static final long serialVersionUID = 1L;
    public static final String MESSAGE = "Hello World !!";

    public String execute() throws Exception {
        setMessage(MESSAGE);

        return "success";
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
