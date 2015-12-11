package com.vc.app.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PasswordAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public PasswordAuthenticator() {
	}

	public PasswordAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}