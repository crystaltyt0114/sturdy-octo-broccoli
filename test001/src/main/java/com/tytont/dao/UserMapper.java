package com.tytont.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tytont.model.Role;
import com.tytont.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	public Role getRoleByUser(@Param("userId") Integer userId);

}
