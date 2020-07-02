package com.tytont.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tytont.dao.PermissionMapper;
import com.tytont.model.Permission;
import com.tytont.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> selectUserByRoleId(Integer roleid) {
		Wrapper<Permission> wrapper = new EntityWrapper<Permission>();
		wrapper.eq("roleid", roleid);
		List<Permission> selectList = permissionMapper.selectList(wrapper);
		return selectList;
	}

}
