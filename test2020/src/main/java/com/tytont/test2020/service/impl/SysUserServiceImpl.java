package com.tytont.test2020.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tytont.test2020.base.BaseServiceImpl;
import com.tytont.test2020.dao.SysUserMapper;
import com.tytont.test2020.model.SysUser;
import com.tytont.test2020.model.query.SysUserQuery;
import com.tytont.test2020.service.SysUserService;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Override
	public boolean create(SysUser entity) {
		return super.save(entity);
	}

	@Override
	public boolean update(SysUser entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean delete(Integer uid) {
		return super.removeById(uid);
	}

	@Override
	public SysUser get(Integer uid) {
		return super.getById(uid);
	}

	@Override
	public List<SysUser> list(SysUserQuery query) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		return super.list(queryWrapper);
	}

	@Override
	public IPage<SysUser> page(SysUserQuery query, IPage<SysUser> page) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		super.page(page, queryWrapper);
		return page;
	}

	@Override
	public SysUser getByUsername(String username) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		queryWrapper.eq(SysUser.USERNAME, username);
		return super.getOne(queryWrapper);
	}

	@Override
	public boolean updatePassword(Integer uid, String password, String newpassword, String newpassword2) {
		SysUser sysUser = this.get(uid);
		sysUser.updatePassword(newpassword);
		return this.updateById(sysUser);
	}

}
