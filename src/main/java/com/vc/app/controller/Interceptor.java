package com.vc.app.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vc.app.util.LoginSession;

public class Interceptor extends HandlerInterceptorAdapter {

  String[] nofilter = new String[] {
    "login", "sys/login", "sys/loginPage", "sys/logout", "user/register"
  };

  /**
   * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
   * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
   * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
   */
  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {

    /* 获取contextPath */
    String ctxPath = request.getContextPath();

    /* 获取uri */
    String uri = request.getRequestURI();

    /* 获取请求动作标识 */
    String actionName = uri.replaceFirst(ctxPath + "/", "");

    /* 个别不需要session的请求，如login */
    Boolean noSession = Arrays.asList(nofilter).contains(actionName);

    /* 获取session中的自定义bean UserSO */
    boolean isLogin = LoginSession.checkSession(request, noSession);
    if (isLogin) {
      request.setAttribute("msg", "登录超时或未登录");
      request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
          response);
      return false;
    }
    return true;
  }

  /**
   * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
   */
  @Override
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler, ModelAndView modelAndView)
      throws Exception {
  }

  /**
   * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
   * 
   * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
   */
  @Override
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
  }

  /**
   * 判断是否为Ajax请求
   * 
   * @param request
   * @return 是true, 否false
   */
  @SuppressWarnings("unused")
  private static boolean isAjaxRequest(HttpServletRequest request) {
    String header = request.getHeader("X-Requested-With");
    if (header != null && "XMLHttpRequest".equals(header)) {
      return true;
    }
    else {
      return false;
    }
  }
}
