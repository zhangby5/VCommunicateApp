package com.vc.app.dao;

import com.vc.app.model.UserInfoVO;

/**
 * 
 * 
 * @version 1.0
 * @since 2015-9-28 11:17:14
 * @author zhangby5
 */
public interface UserInfoVOMapper {

  int deleteByPrimaryKey(String cuserid);

  int insert(UserInfoVO record);

  int insertSelective(UserInfoVO record);

  UserInfoVO selectByPrimaryKey(String cuserid);
  
  String selectByUsername(String cusername);
  
  String selectByEmail(String cemail);

  int updateByPrimaryKeySelective(UserInfoVO record);

  int updateByPrimaryKey(UserInfoVO record);
}
