package com.tytont.test2020.base;

import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<M extends BaseDao<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
	
	public T getOne(Consumer<QueryWrapper<T>> consumer) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		consumer.accept(queryWrapper);
		return this.getOne(queryWrapper);
	}
	
	public List<T> list(Consumer<QueryWrapper<T>> consumer) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		consumer.accept(queryWrapper);
		return this.list(queryWrapper);
	}
	
	public T lambdaOne(Consumer<LambdaQueryChainWrapper<T>> consumer) {
		LambdaQueryChainWrapper<T> lambdaQuery = this.lambdaQuery();
		consumer.accept(lambdaQuery);
		return lambdaQuery.one();
	}
	
	public List<T> lambdaList(Consumer<LambdaQueryChainWrapper<T>> consumer){
		LambdaQueryChainWrapper<T> lambdaQuery = this.lambdaQuery();
		consumer.accept(lambdaQuery);
		return lambdaQuery.list();
	}
	
}
