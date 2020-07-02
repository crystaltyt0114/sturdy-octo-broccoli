package com.tytont.test.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

//redisTemplate.opsForHash();//操作hash
//Redis的散列可以让用户将多个键值对存储到一个Redis键里面。
//public interface HashOperations<H,HK,HV> :HashOperations提供一系列方法操作hash：
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHash {

	@Resource
	RedisTemplate<String, Object> template;

	public void test01() {
		template.opsForHash().put("redisHash", "name", "tom");
		template.opsForHash().put("redisHash", "age", 26);
		template.opsForHash().put("redisHash", "class", "6");

		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("name", "jack");
		testMap.put("age", 27);
		testMap.put("class", "1");
		template.opsForHash().putAll("redisHash1", testMap);
	}

	// Long delete(H key, Object... hashKeys);
	// 删除给定的哈希hashKeys
	public void test02() {
		System.out.println(template.opsForHash().delete("redisHash", "name"));
		System.out.println(template.opsForHash().entries("redisHash"));
	}

	// Long delete(H key, Object... hashKeys);
	// 删除给定的哈希hashKeys
	public void test03() {
		System.out.println(template.opsForHash().delete("redisHash", "name"));
		System.out.println(template.opsForHash().entries("redisHash"));
	}

	// Boolean hasKey(H key, Object hashKey);
	// 确定哈希hashKey是否存在
	public void test04() {
		System.out.println(template.opsForHash().hasKey("redisHash", "age"));
		System.out.println(template.opsForHash().hasKey("redisHash", "ttt"));
	}

	// HV get(H key, Object hashKey);
	// 从键中的哈希获取给定hashKey的值
	public void test05() {
		System.out.println(template.opsForHash().get("redisHash", "age"));
	}

	// List<HV> multiGet(H key, Collection<HK> hashKeys);
	// 从哈希中获取给定hashKey的值
	public void test06() {
		List<Object> kes = new ArrayList<Object>();
		kes.add("name");
		kes.add("age");
		System.out.println(template.opsForHash().multiGet("redisHash", kes));
	}

	// Long increment(H key, HK hashKey, long delta);
	// 通过给定的delta增加散列hashKey的值（整型）
	public void test07() {
		System.out.println(template.opsForHash().get("redisHash", "age"));
		System.out.println(template.opsForHash().increment("redisHash", "age", 1));

	}

	// Double increment(H key, HK hashKey, double delta);
	// 通过给定的delta增加散列hashKey的值（浮点数）
	public void test08() {
		System.out.println(template.opsForHash().get("redisHash", "age"));
		System.out.println(template.opsForHash().increment("redisHash", "age", 1.1));

	}

	// Set<HK> keys(H key);
	// 获取key所对应的散列表的key
	public void test09() {
		System.out.println(template.opsForHash().keys("redisHash1"));
	}

	// Long size(H key);
	// 获取key所对应的散列表的大小个数
	public void test10() {
		System.out.println(template.opsForHash().size("redisHash1"));
	}

	// void putAll(H key, Map<? extends HK, ? extends HV> m);
	// 使用m中提供的多个散列字段设置到key对应的散列表中
	public void test11() {
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("name", "jack");
		testMap.put("age", 27);
		testMap.put("class", "1");
		template.opsForHash().putAll("redisHash1", testMap);
		System.out.println(template.opsForHash().entries("redisHash1"));

	}

	// void put(H key, HK hashKey, HV value);
	// 设置散列hashKey的值
	public void test12() {
		template.opsForHash().put("redisHash", "name", "tom");
		template.opsForHash().put("redisHash", "age", 26);
		template.opsForHash().put("redisHash", "class", "6");
		System.out.println(template.opsForHash().entries("redisHash"));
	}

	// Boolean putIfAbsent(H key, HK hashKey, HV value);
	// 仅当hashKey不存在时才设置散列hashKey的值。
	public void test13() {
		System.out.println(template.opsForHash().putIfAbsent("redisHash", "age", 30));
		System.out.println(template.opsForHash().putIfAbsent("redisHash", "kkk", "kkk"));
	}

	// List<HV> values(H key);
	// 获取整个哈希存储的值 根据密钥
	public void test14() {
		System.out.println(template.opsForHash().values("redisHash"));
	}

	// Map<HK, HV> entries(H key);
	// 获取整个哈希存储 根据密钥
	public void test15() {
		System.out.println(template.opsForHash().entries("redisHash"));
	}

	@Test
	// Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options);
	// 使用Cursor在key的hash中迭代，相当于迭代器。
	public void test16() {
		Cursor<Map.Entry<Object, Object>> curosr = template.opsForHash().scan("redisHash", ScanOptions.NONE);
		while (curosr.hasNext()) {
			Map.Entry<Object, Object> entry = curosr.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
