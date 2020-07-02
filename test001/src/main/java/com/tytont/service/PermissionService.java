package com.tytont.service;

import java.util.List;

import com.tytont.model.Permission;

public interface PermissionService {

	public List<Permission> selectUserByRoleId(Integer roleid);
}
