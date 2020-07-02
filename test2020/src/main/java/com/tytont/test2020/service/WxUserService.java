package com.tytont.test2020.service;

import java.util.List;

import com.tytont.test2020.base.BaseService;
import com.tytont.test2020.model.WxUser;
import com.tytont.test2020.model.query.WxUserQuery;

public interface WxUserService extends BaseService<WxUser> {

	boolean create(WxUser entity);

	boolean update(WxUser entity);

	boolean delete(Integer id);

	WxUser get(Integer id);

	List<WxUser> list(WxUserQuery query);

}
