package com.tytont.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tytont.dao.RoleMapper;
import com.tytont.model.Role;
import com.tytont.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role selectById(Integer roleid) {
		// TODO Auto-generated method stub
		return roleMapper.selectById(roleid);
	}

}
