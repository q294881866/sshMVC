package test;

import org.jiumao.redis.JedisPoolUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 测试redis连接
 * @author Administrator
 *
 */
public class Test4Redis {

	public static void main(String[] args) {
		Jedis jedis = JedisPoolUtils.getConnection();
		System.out.println(jedis);
	}
}
