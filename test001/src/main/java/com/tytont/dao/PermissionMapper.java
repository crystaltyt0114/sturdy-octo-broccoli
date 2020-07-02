package com.tytont.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tytont.model.Permission;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
