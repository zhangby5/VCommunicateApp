package com.vc.app.dao;

import com.vc.app.model.UserRelationVO;

public interface UserRelationVOMapper {
    int deleteByPrimaryKey(String cuserid);

    int insert(UserRelationVO record);

    int insertSelective(UserRelationVO record);

    UserRelationVO selectByPrimaryKey(String cuserid);

    int updateByPrimaryKeySelective(UserRelationVO record);

    int updateByPrimaryKey(UserRelationVO record);
}