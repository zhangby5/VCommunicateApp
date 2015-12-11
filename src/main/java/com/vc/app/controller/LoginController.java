package com.vc.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vc.app.model.UserInfoVO;
import com.vc.app.service.LoginService;

@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController{
  
  @Autowired
  private LoginService loginService;
  
  @RequestMapping(value = "login")
  public String login(
      @RequestParam(value = "username", required = false) String username,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    System.out.println(request.getParameter("aaa"));
    request.setAttribute("aaa", username);
    return "index";
  }
  
  @RequestMapping(value = "register")
  public String register(@ModelAttribute("userinfo") UserInfoVO userinfo,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if(loginService.isExsitUsername(userinfo.getCusername())){
      
    }else if(loginService.isExsitEmail(userinfo.getCemail())){
      
    }
    loginService.addUser(request, userinfo);
    return "login";
  }
  
  public LoginService getLoginService() {
    return this.loginService;
  }

  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
  }

}
