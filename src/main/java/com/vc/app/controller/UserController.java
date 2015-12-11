package com.vc.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
public class UserController {

  @RequestMapping(value = "/login")
  public String login(
      @RequestParam(value = "username", required = false) String username,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    System.out.println(request.getParameter("aaa"));
    request.setAttribute("aaa", username);
    return "index";
  }
}
