package com.tytont.test.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

//redisTemplate.opsForValue();//操作字符串
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestString {

	@Resource
	RedisTemplate<String, Object> redisTemplate;

	public void testSet() {
		redisTemplate.opsForValue().set("name", "tom");
		redisTemplate.opsForValue().get("name");
	}

	// 该方法是用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
	public void testSet2() {
		redisTemplate.opsForValue().set("key1", "hello world");
		redisTemplate.opsForValue().set("key1", "redis", 6);
		System.out.println(redisTemplate.opsForValue().get("key1"));
	}

	// setIfAbsent如果不存在，则插入
	public void testSet3() {
		System.out.println(redisTemplate.opsForValue().setIfAbsent("multi1", "multi1"));
		System.out.println(redisTemplate.opsForValue().setIfAbsent("multi111", "multi111"));
	}

	// multiSet:批量插入
	// multiGet:批量取出
	public void testSet4() {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("multi1", "multi1");
		maps.put("multi2", "multi2");
		maps.put("multi3", "multi3");
		redisTemplate.opsForValue().multiSet(maps);
		List<String> keys = new ArrayList<String>();
		keys.add("multi1");
		keys.add("multi2");
		System.out.println(redisTemplate.opsForValue().multiGet(keys));

	}

	// multiSetIfAbsent:为多个键分别设置它们的值，如果存在则返回false，不存在返回true
	public void testSet5() {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("multi11", "multi11");
		maps.put("multi22", "multi22");
		maps.put("multi33", "multi33");
		Map<String, String> maps2 = new HashMap<String, String>();
		maps2.put("multi1", "multi1");
		maps2.put("multi2", "multi2");
		maps2.put("multi3", "multi3");
		System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(maps));
		System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(maps2));
	}

	// get(Object key);
	public void testSet6() {
		redisTemplate.opsForValue().set("key", "hello world");
		System.out.println(redisTemplate.opsForValue().get("key"));
	}

	// getAndSet(K key, V value):设置键的字符串值并返回其旧值
	public void testSet7() {
		redisTemplate.opsForValue().set("getSetTest", "test");
		System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest", "test2"));
	}

	// multiGet(Collection<K> keys):取出多个的值
	public void testSet8() {
		List<String> keys = new ArrayList<String>();
		keys.add("multi1");
		keys.add("multi2");
		keys.add("multi3");
		System.out.println(redisTemplate.opsForValue().multiGet(keys));
	}

	// increment Long increment(K key, long delta):支持整数增加
	public void testSet9() {
		redisTemplate.opsForValue().increment("increlong", 1);
	}

	// increment Double increment(K key, double delta):支持浮点数增加
	public void testSet10() {
		redisTemplate.opsForValue().increment("increlong", 1.2);
	}

	// append Integer append(K key, String value);
	// 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
	public void testSet11() {
		redisTemplate.opsForValue().append("appendTest", "Hello");
		System.out.println(redisTemplate.opsForValue().get("appendTest"));
		redisTemplate.opsForValue().append("appendTest", "world");
		System.out.println(redisTemplate.opsForValue().get("appendTest"));

	}

	// get String get(K key, long start, long end);
	// 截取key所对应的value字符串
	public void testSet12() {
		System.out.println("*********" + redisTemplate.opsForValue().get("appendTest", 0, 5));
		System.out.println("*********" + redisTemplate.opsForValue().get("appendTest", 0, -1));
		System.out.println("*********" + redisTemplate.opsForValue().get("appendTest", -3, -1));
	}

	// size Long size(K key);
	// 返回key所对应的value值得长度
	public void testSet13() {
		System.out.println("***************" + redisTemplate.opsForValue().size("appendTest"));
	}

	@Test
	// setBit Boolean setBit(K key, long offset, boolean value);
	// 对 key所储存的字符串值，设置或清除指定偏移量上的位(bit),
	// key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
	public void testSet14() {
		redisTemplate.opsForValue().set("bitTest", "a");
		// 'a' 的ASCII码是 97。转换为二进制是：01100001
		// 'b' 的ASCII码是 98 转换为二进制是：01100010
		// 'c' 的ASCII码是 99 转换为二进制是：01100011
		// 因为二进制只有0和1，在setbit中true为1，false为0，因此我要变为'b'的话第六位设置为1，第七位设置为0
		redisTemplate.opsForValue().setBit("bitTest", 6, true);
		redisTemplate.opsForValue().setBit("bitTest", 7, false);
		System.out.println(redisTemplate.opsForValue().get("bitTest"));
	}

	@Test
	// getBit Boolean getBit(K key, long offset);
	// 获取键对应值的ascii码的在offset处位值
	public void testSet15() {
		System.out.println(redisTemplate.opsForValue().getBit("bitTest", 7));
	}
}
