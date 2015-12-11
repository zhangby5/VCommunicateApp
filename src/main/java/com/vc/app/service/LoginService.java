package com.vc.app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vc.app.dao.UserInfoVOMapper;
import com.vc.app.model.UserInfoVO;

@Service("loginService")
public class LoginService {
  
  @Autowired
  private UserInfoVOMapper userinfoMapper;

  @Transactional(rollbackFor = Exception.class)
  public UserInfoVO addUser(HttpServletRequest request, UserInfoVO userinfo) {
    userinfoMapper.insert(userinfo);
    return userinfo;
  }

  @Transactional(rollbackFor = Exception.class)
  public boolean isExsitUsername(String username) {
    String userid = userinfoMapper.selectByUsername(username);
    if (StringUtils.isEmpty(userid)) {
      return false;
    }
    return true;
  }
  
  @Transactional(rollbackFor = Exception.class)
  public boolean isExsitEmail(String email) {
    String userid = userinfoMapper.selectByEmail(email);
    if (StringUtils.isEmpty(userid)) {
      return false;
    }
    return true;
  }
  
}
