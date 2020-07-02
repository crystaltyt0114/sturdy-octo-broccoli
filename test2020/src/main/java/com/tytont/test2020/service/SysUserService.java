package com.tytont.test2020.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tytont.test2020.base.BaseService;
import com.tytont.test2020.model.SysUser;
import com.tytont.test2020.model.query.SysUserQuery;

public interface SysUserService extends BaseService<SysUser> {

	boolean create(SysUser entity);

	boolean update(SysUser entity);

	boolean delete(Integer uid);

	SysUser get(Integer uid);

	List<SysUser> list(SysUserQuery query);

	IPage<SysUser> page(SysUserQuery query, IPage<SysUser> page);

	SysUser getByUsername(String username);

	boolean updatePassword(Integer uid, String password, String newpassword, String newpassword2);

}
