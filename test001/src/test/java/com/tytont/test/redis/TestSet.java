package com.tytont.test.redis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

//redisTemplate.opsForSet();//操作set
//Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
//Redis 中 集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
//public interface SetOperations<K,V>
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSet {
	@Resource
	RedisTemplate<String, Object> template;

	// Long add(K key, V... values);
	// 无序集合中添加元素，返回添加个数
	// 也可以直接在add里面添加多个值 如：template.opsForSet().add("setTest","aaa","bbb")
	public void test01() {
		String[] strarrays = new String[] { "strarr1", "sgtarr2" };
		System.out.println(template.opsForSet().add("setTest", strarrays));
	}

	// Long remove(K key, Object... values);
	// 移除集合中一个或多个成员
	public void test02() {
		String[] strarrays = new String[] { "strarr1", "sgtarr2" };
		System.out.println(template.opsForSet().remove("setTest", strarrays));
	}

	// V pop(K key);
	// 移除并返回集合中的一个随机元素
	public void test03() {
		// template.opsForSet().add("setTest", "aaa", "bbb", "ccc");
		System.out.println(template.opsForSet().pop("setTest"));
		System.out.println(template.opsForSet().members("setTest"));
	}

	// Boolean move(K key, V value, K destKey);
	// 将 member 元素从 source 集合移动到 destination 集合
	public void test04() {
		template.opsForSet().move("setTest", "bbb", "setTest2");
		System.out.println(template.opsForSet().members("setTest"));
		System.out.println(template.opsForSet().members("setTest2"));

	}

	// Long size(K key);
	// 无序集合的大小长度
	public void test05() {
		System.out.println(template.opsForSet().size("setTest"));
	}

	// Boolean isMember(K key, Object o);
	// 判断 member 元素是否是集合 key 的成员
	public void test06() {
		System.out.println(template.opsForSet().isMember("setTest", "ccc"));
		System.out.println(template.opsForSet().isMember("setTest", "asd"));
	}

	// Set<V> intersect(K key, K otherKey);
	// key对应的无序集合与otherKey对应的无序集合求交集
	public void test07() {
		System.out.println(template.opsForSet().members("setTest"));
		System.out.println(template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().intersect("setTest", "setTest2"));
	}

	// Set<V> intersect(K key, Collection<K> otherKeys);
	// key对应的无序集合与多个otherKey对应的无序集合求交集
	public void test08() {
		System.out.println(template.opsForSet().members("setTest"));
		System.out.println(template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().members("setTest3"));
		List<String> strlist = new ArrayList<String>();
		strlist.add("setTest2");
		strlist.add("setTest3");
		System.out.println(template.opsForSet().intersect("setTest", strlist));

	}

	// Long intersectAndStore(K key, K otherKey, K destKey);
	// key无序集合与otherkey无序集合的交集存储到destKey无序集合中
	public void test09() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().intersectAndStore("setTest", "setTest2", "destKey1"));
		System.out.println(template.opsForSet().members("destKey1"));

	}

	// Long intersectAndStore(K key, Collection<K> otherKeys, K destKey);
	// key对应的无序集合与多个otherKey对应的无序集合求交集存储到destKey无序集合中
	public void test10() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println("setTest3:" + template.opsForSet().members("setTest3"));
		List<String> strlist = new ArrayList<String>();
		strlist.add("setTest2");
		strlist.add("setTest3");
		System.out.println(template.opsForSet().intersectAndStore("setTest", strlist, "destKey2"));
		System.out.println(template.opsForSet().members("destKey2"));
	}

	// Set<V> union(K key, K otherKey);
	// key无序集合与otherKey无序集合的并集
	public void test11() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().union("setTest", "setTest2"));
	}

	// Set<V> union(K key, Collection<K> otherKeys);
	// key无序集合与多个otherKey无序集合的并集
	public void test12() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println("setTest3:" + template.opsForSet().members("setTest3"));
		List<String> strlist = new ArrayList<String>();
		strlist.add("setTest2");
		strlist.add("setTest3");
		System.out.println(template.opsForSet().union("setTest", strlist));
	}

	// Long unionAndStore(K key, K otherKey, K destKey);
	// key无序集合与otherkey无序集合的并集存储到destKey无序集合中
	public void test13() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().unionAndStore("setTest", "setTest2", "unionAndStoreTest1"));
		System.out.println("unionAndStoreTest1:" + template.opsForSet().members("unionAndStoreTest1"));
	}

	// Long unionAndStore(K key, Collection<K> otherKeys, K destKey);
	// key无序集合与多个otherkey无序集合的并集存储到destKey无序集合中
	public void test14() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println("setTest3:" + template.opsForSet().members("setTest3"));
		List<String> strlist = new ArrayList<String>();
		strlist.add("setTest2");
		strlist.add("setTest3");
		System.out.println(template.opsForSet().unionAndStore("setTest", strlist, "unionAndStoreTest2"));
		System.out.println("unionAndStoreTest2:" + template.opsForSet().members("unionAndStoreTest2"));

	}

	// Set<V> difference(K key, K otherKey);
	// key无序集合与otherKey无序集合的差集
	public void test15() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().difference("setTest", "setTest2"));

	}

	// Set<V> difference(K key, Collection<K> otherKeys);
	// key无序集合与多个otherKey无序集合的差集
	public void test16() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println("setTest3:" + template.opsForSet().members("setTest3"));
		List<String> strlist = new ArrayList<String>();
		strlist.add("setTest2");
		strlist.add("setTest3");
		System.out.println(template.opsForSet().difference("setTest", strlist));
	}

	// Long differenceAndStore(K key, K otherKey, K destKey);
	// key无序集合与otherkey无序集合的差集存储到destKey无序集合中
	public void test17() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println(template.opsForSet().differenceAndStore("setTest", "setTest2", "differenceAndStore1"));
		System.out.println("differenceAndStore1:" + template.opsForSet().members("differenceAndStore1"));

	}

	// Long differenceAndStore(K key, Collection<K> otherKeys, K destKey);
	// key无序集合与多个otherkey无序集合的差集存储到destKey无序集合中
	public void test18() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTest2:" + template.opsForSet().members("setTest2"));
		System.out.println("setTest3:" + template.opsForSet().members("setTest3"));
		List<String> strlist = new ArrayList<String>();
		strlist.add("setTest2");
		strlist.add("setTest3");
		System.out.println(template.opsForSet().differenceAndStore("setTest", strlist, "differenceAndStore2"));
		System.out.println("differenceAndStore2:" + template.opsForSet().members("differenceAndStore2"));

	}

	// Set<V> members(K key);
	// 返回集合中的所有成员
	public void test19() {
		System.out.println(template.opsForSet().members("setTest"));
	}

	// V randomMember(K key);
	// 随机获取key无序集合中的一个元素
	public void test20() {
		System.out.println("setTest:" + template.opsForSet().members("setTest"));
		System.out.println("setTestrandomMember:" + template.opsForSet().randomMember("setTest"));
		System.out.println("setTestrandomMember:" + template.opsForSet().randomMember("setTest"));
		System.out.println("setTestrandomMember:" + template.opsForSet().randomMember("setTest"));
		System.out.println("setTestrandomMember:" + template.opsForSet().randomMember("setTest"));

	}

	// Set<V> distinctRandomMembers(K key, long count);
	// 获取多个key无序集合中的元素（去重），count表示个数
	public void test21() {
		System.out.println("randomMembers:" + template.opsForSet().distinctRandomMembers("setTest", 2));
	}

	// List<V> randomMembers(K key, long count);
	// 获取多个key无序集合中的元素，count表示个数
	public void test22() {
		System.out.println("randomMembers:" + template.opsForSet().randomMembers("setTest", 2));
	}

	@Test
	// Cursor<V> scan(K key, ScanOptions options);
	// 遍历set
	public void test23() {
		Cursor<Object> curosr = template.opsForSet().scan("setTest", ScanOptions.NONE);
		while (curosr.hasNext()) {
			System.out.println(curosr.next());
		}

	}

}
