package com.tytont.test2020.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tytont.test2020.base.BaseServiceImpl;
import com.tytont.test2020.dao.WxUserMapper;
import com.tytont.test2020.model.WxUser;
import com.tytont.test2020.model.query.WxUserQuery;
import com.tytont.test2020.service.WxUserService;

@Service
public class WxUserServiceImpl extends BaseServiceImpl<WxUserMapper, WxUser> implements WxUserService {

	@Override
	public boolean create(WxUser entity) {
		return super.save(entity);
	}

	@Override
	public boolean update(WxUser entity) {
		return super.updateById(entity);
	}

	@Override
	public boolean delete(Integer id) {
		return super.removeById(id);
	}

	@Override
	public WxUser get(Integer id) {
		return super.getById(id);
	}

	@Override
	public List<WxUser> list(WxUserQuery query) {
		QueryWrapper<WxUser> queryWrapper = new QueryWrapper<WxUser>();
		return super.list(queryWrapper);
	}

}
