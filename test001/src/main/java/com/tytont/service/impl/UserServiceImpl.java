package com.tytont.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tytont.dao.UserMapper;
import com.tytont.model.Role;
import com.tytont.model.User;
import com.tytont.service.UserService;
import com.tytont.utli.RedisKeyPrefix;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Resource
	private RedisTemplate<String, User> redisTemplate;

	@Override
	public User getUser(int id) {
		String key = RedisKeyPrefix.USER + id;
		// 缓存存在
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) { // 从缓存中取
			User user = redisTemplate.opsForValue().get(key);
			System.out.println("从缓存中获取了用户！");
			return user;
		}
		// 从数据库取，并存回缓存
		User user = userMapper.selectById(id);
		// 放入缓存，并设置缓存时间
		redisTemplate.opsForValue().set(key, user, 600, TimeUnit.SECONDS);
		return user;
	}

	@Override
	public Object page(Integer pageNum, Integer pageSize) {
		Wrapper<User> wrapper = new EntityWrapper<User>();
		Page<User> page = new Page<User>((pageNum - 1) * pageSize, pageSize);
		return userMapper.selectPage(page, wrapper);

	}

	@Override
	public User selectUserByUsername(String username, String password) {
		Wrapper<User> wrapper = new EntityWrapper<User>();
		wrapper.eq(User.USERNAME, username);
		wrapper.eq(User.PASSWORD, password);
		List<User> selectList = userMapper.selectList(wrapper);
		return selectList == null || selectList.size() == 0 ? null : selectList.get(0);
	}

	@Override
	public User add() {
		User user = new User();
		user.setUsername("sss");
		user.setSex("nan");
		user.setUpdate_time(new Date());
		userMapper.insert(user);
		return user;
	}

	@Override
	public int update() {
		User user = userMapper.selectById(1);
		user.setUsername("aaa");
		userMapper.updateById(user);
		return 0;
	}

	@Override
	public Role getRoleByUser(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getRoleByUser(userId);
	}

}
