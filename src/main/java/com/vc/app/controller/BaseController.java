package com.vc.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 
 * @version 1.0
 * @since 2015-10-2 下午11:17:03
 * @author zhangby5
 */
public class BaseController {

	private static Logger logger = Logger.getLogger(BaseController.class);

	@ExceptionHandler
	public String exception(HttpServletRequest request, Exception e) {
		request.setAttribute("exception", e.getMessage());
		logger.error(e.getMessage());
		if (e instanceof Exception) {
			return "errors/error";
		} else if (e instanceof SystemException) {
			return "errors/error";
		}
		return "errors/error";
	}

	protected static void returnMsgJs(HttpServletResponse response, String msg) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 404页面
	 * 
	 * @return
	 */
	protected ModelAndView page404() {
		return new ModelAndView("errors/404");
	}
	
	private static final String ErrorJsonString = "{\"status\":\"%%STATUS%%\", \"msg\":\"%%MSG%%\"}";
	
	protected String getErrorJsonString(boolean status, String msg) {
	    return ErrorJsonString.replace("%%STATUS%%", String.valueOf(status)).replace("%%MSG%%", msg);
	}
}
