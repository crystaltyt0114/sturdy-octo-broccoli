package com.tytont.test.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

//Redis 有序集合和无序集合一样也是string类型元素的集合,且不允许重复的成员。
//不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
//有序集合的成员是唯一的,但分数(score)却可以重复。
//public interface ZSetOperations<K,V>
//ZSetOperations提供了一系列方法对有序集合进行操作：
//redisTemplate.opsForZSet();//操作有序set
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestZSet {
	@Autowired
	RedisTemplate<String, Object> template;

	// Boolean add(K key, V value, double score);
	// 新增一个有序集合，存在的话为false，不存在的话为true
	public void test01() {
		System.out.println(template.opsForZSet().add("zset1", "zset-1", 1.0));
	}

	// Long add(K key, Set<TypedTuple<V>> tuples);
	// 新增一个有序集合
	public void test02() {
		ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-5", 9.6);
		ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-6", 9.9);
		Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
		tuples.add(objectTypedTuple1);
		tuples.add(objectTypedTuple2);
		System.out.println(template.opsForZSet().add("zset1", tuples));
		System.out.println(template.opsForZSet().range("zset1", 0, -1));

	}

	// Long remove(K key, Object... values);
	// 从有序集合中移除一个或者多个元素
	public void test03() {
		System.out.println(template.opsForZSet().range("zset1", 0, -1));
		System.out.println(template.opsForZSet().remove("zset1", "zset-6"));
		System.out.println(template.opsForZSet().range("zset1", 0, -1));
	}

	// Double incrementScore(K key, V value, double delta);
	// 增加元素的score值，并返回增加后的值
	public void test04() {
		System.out.println(template.opsForZSet().incrementScore("zset1", "zset-1", 1.1));

	}

	// Long rank(K key, Object o);
	// 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test05() {
		System.out.println(template.opsForZSet().range("zset1", 0, -1));
		System.out.println(template.opsForZSet().rank("zset1", "zset-1"));
	}

	// Long reverseRank(K key, Object o);
	// 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test06() {
		System.out.println(template.opsForZSet().reverseRange("zset1", 0, -1));
	}

	// Set<V> range(K key, long start, long end);
	// 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test07() {
		System.out.println(template.opsForZSet().range("zset1", 0, -1));
	}

	// Set<TypedTuple<V>> rangeWithScores(K key, long start, long end);
	// 通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test08() {
		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().rangeWithScores("zset1", 0, -1);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
		}
	}

	// Set<V> rangeByScore(K key, double min, double max);
	// 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test09() {
		System.out.println(template.opsForZSet().rangeByScore("zset1", 0, 5));
	}

	// Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max);
	// 通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test10() {
		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().rangeByScoreWithScores("zset1", 0, 5);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
		}
	}

	// Set<V> rangeByScore(K key, double min, double max, long offset, long
	// count);
	// 通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test11() {
		System.out.println(template.opsForZSet().rangeByScore("zset1", 0, 10));
		System.out.println(template.opsForZSet().rangeByScore("zset1", 0, 10, 1, 2));
	}

	// Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max,
	// long offset, long count);
	// 通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test12() {
		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().rangeByScoreWithScores("zset1", 0, 10, 1,
				2);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
		}
	}

	// Set<V> reverseRange(K key, long start, long end);
	// 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test13() {
		System.out.println(template.opsForZSet().reverseRange("zset1", 0, -1));
	}

	// Set<TypedTuple<V>> reverseRangeWithScores(K key, long start, long end);
	// 通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test14() {
		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().reverseRangeWithScores("zset1", 0, -1);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
		}
	}

	// Set<V> reverseRangeByScore(K key, double min, double max);
	// 使用：与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test15() {

	}

	// Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min,
	// double max);
	// 使用：与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test16() {

	}

	// Set<V> reverseRangeByScore(K key, double min, double max, long offset,
	// long count);
	// 使用：与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test17() {

	}

	// Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min,
	// double max, long offset, long count);
	// 使用：与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
	public void test18() {

	}

	// Long count(K key, double min, double max);
	// 通过分数返回有序集合指定区间内的成员个数
	public void test19() {
		System.out.println(template.opsForZSet().rangeByScore("zset1", 0, 5));
		System.out.println(template.opsForZSet().count("zset1", 0, 5));

	}

	// Long size(K key);
	// 获取有序集合的成员数，内部调用的就是zCard方法
	public void test20() {
		System.out.println(template.opsForZSet().size("zset1"));
	}

	// Long zCard(K key);
	// 获取有序集合的成员数
	public void test21() {
		System.out.println(template.opsForZSet().zCard("zset1"));
	}

	// Double score(K key, Object o);
	// 获取指定成员的score值
	public void test22() {
		System.out.println(template.opsForZSet().score("zset1", "zset-1"));
	}

	// Long removeRange(K key, long start, long end);
	// 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
	public void test23() {
		System.out.println(template.opsForZSet().range("zset2", 0, -1));
		System.out.println(template.opsForZSet().removeRange("zset2", 1, 2));
		System.out.println(template.opsForZSet().range("zset2", 0, -1));
	}

	// Long removeRangeByScore(K key, double min, double max);
	// 根据指定的score值得范围来移除成员
	public void test24() {
		System.out.println(template.opsForZSet().range("zset2", 0, -1));
		System.out.println(template.opsForZSet().removeRangeByScore("zset2", 2, 3));
		System.out.println(template.opsForZSet().range("zset2", 0, -1));
	}

	// Long unionAndStore(K key, K otherKey, K destKey);
	// 计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
	public void test25() {
		System.out.println(template.opsForZSet().add("zzset1", "zset-1", 1.0));
		System.out.println(template.opsForZSet().add("zzset1", "zset-2", 2.0));
		System.out.println(template.opsForZSet().add("zzset1", "zset-3", 3.0));
		System.out.println(template.opsForZSet().add("zzset1", "zset-4", 6.0));

		System.out.println(template.opsForZSet().add("zzset2", "zset-1", 1.0));
		System.out.println(template.opsForZSet().add("zzset2", "zset-2", 2.0));
		System.out.println(template.opsForZSet().add("zzset2", "zset-3", 3.0));
		System.out.println(template.opsForZSet().add("zzset2", "zset-4", 6.0));
		System.out.println(template.opsForZSet().add("zzset2", "zset-5", 7.0));
		System.out.println(template.opsForZSet().unionAndStore("zzset1", "zzset2", "destZset11"));

		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().rangeWithScores("destZset11", 0, -1);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + ",score:" + typedTuple.getScore());
		}

	}

	// Long unionAndStore(K key, Collection<K> otherKeys, K destKey);
	// 计算给定的多个有序集的并集，并存储在新的 destKey中
	public void test26() {
		System.out.println(template.opsForZSet().add("zzset3", "zset-1", 1.0));
		System.out.println(template.opsForZSet().add("zzset3", "zset-2", 2.0));
		System.out.println(template.opsForZSet().add("zzset3", "zset-3", 3.0));
		System.out.println(template.opsForZSet().add("zzset3", "zset-4", 6.0));
		System.out.println(template.opsForZSet().add("zzset3", "zset-5", 7.0));

		List<String> stringList = new ArrayList<String>();
		stringList.add("zzset2");
		stringList.add("zzset3");
		System.out.println(template.opsForZSet().unionAndStore("zzset1", stringList, "destZset22"));

		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().rangeWithScores("destZset22", 0, -1);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + ",score:" + typedTuple.getScore());
		}
	}

	@Test
	// Long intersectAndStore(K key, K otherKey, K destKey);
	// 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
	public void test27() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("zzset2");
		stringList.add("zzset3");
		System.out.println(template.opsForZSet().intersectAndStore("zzset1", stringList, "destZset44"));

		Set<ZSetOperations.TypedTuple<Object>> tuples = template.opsForZSet().rangeWithScores("destZset44", 0, -1);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
		while (iterator.hasNext()) {
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
		}
	}

	// Cursor<TypedTuple<V>> scan(K key, ScanOptions options);
	// 遍历zset
	public void test28() {
		Cursor<ZSetOperations.TypedTuple<Object>> cursor = template.opsForZSet().scan("zzset1", ScanOptions.NONE);
		while (cursor.hasNext()) {
			ZSetOperations.TypedTuple<Object> item = cursor.next();
			System.out.println(item.getValue() + ":" + item.getScore());
		}
	}

	// 注：TimeUnit是java.util.concurrent包下面的一个类，表示给定单元粒度的时间段
	// 常用的颗粒度
	// TimeUnit.DAYS //天
	// TimeUnit.HOURS //小时
	// TimeUnit.MINUTES //分钟
	// TimeUnit.SECONDS //秒
	// TimeUnit.MILLISECONDS //毫秒
}
