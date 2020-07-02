package com.tytont.test2020.base;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * code is far away from bug with the animal protecting
 */
public interface BaseDao<T> extends BaseMapper<T> {

	List<T> selectPageWithParam(Page<T> page, T param);
	
	
}
