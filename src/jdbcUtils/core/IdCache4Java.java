package jdbcUtils.core;

import java.util.ArrayList;
import java.util.List;

import org.jiumao.redis.domain.RedisServiceImpl;
import org.jiumao.talentMarket.service.GoodsServiceImpl;

/**
 * 配合redis分页实现，<br>
 * 用Java的ArrayList缓存数据库的id这样可以轻松实现分页<br>
 * 每个数据库的不同表id用不同的实例来缓存<br>
 * 要求每次服务器重启时，执行。4字节的int类型500w条是20MB
 * 
 * @author Administrator
 *
 */
public class IdCache4Java {
	
	public static List<RedisServiceImpl> redisServiceImpls = new ArrayList<>();
	public static void  init() throws Exception{
		IdCache4Java.redisServiceImpls.add(new GoodsServiceImpl().redisService);
		for (RedisServiceImpl iterable_element : redisServiceImpls) {
			iterable_element.init();
		}
	}
	
	public static List<Integer> creatIdCache(String databaseName, String tableName,
			List<Integer> list){
		return new DataType(databaseName, tableName, list).list;
	}
	

	private static class DataType{
		String databaseName;
		String tableName;
		List<Integer> list=null;
		public DataType(String databaseName, String tableName,
				List<Integer> list) {
			super();
			this.databaseName = databaseName;
			this.tableName = tableName;
			this.list = list;
		} 
		
	}

}

