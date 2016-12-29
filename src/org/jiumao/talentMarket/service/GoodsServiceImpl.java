package org.jiumao.talentMarket.service;

import java.util.ArrayList;
import java.util.List;

import org.jiumao.redis.domain.RedisServiceImpl;
import org.jiumao.talentMarket.domain.Goods;

import base.BaseServiceImpl;

public class GoodsServiceImpl extends BaseServiceImpl<Goods>  implements GoodsService {
	private static List<Integer> integers ;
	public String tableName = "goods";

	public int delete(Integer id) throws Exception {
		 int i = baseDao.delete(id);
		 if (i>0) {
			redisService.delEntryString(tableName, id+"");
			integers.remove(id);
		}
		 return i;
	}

	public int updateById(Integer id,Object... parameters) throws Exception {
		 int i = baseDao.updateById(id,parameters);
		 Goods goods = getById(id);
		 String s = redisService.toString(goods);
		 redisService.setEntry(tableName, id+"", s);
		 return i;
	}

	public Goods getById(Integer id) throws Exception {
		String s = redisService.getEntryString(tableName, id+"");
		if (null == s) { // 缓存中没有查询到数据
			Goods goods = baseDao.getById(id);
			String objOfString = redisService.toString(goods);
			System.out.println(objOfString);
			redisService.setEntry(tableName, id + "", objOfString);
			return goods;
		} else {
			return (Goods) redisService.getEntryByRedisString(s);
		}
	}

	public List<Goods> getByIds(Integer[] ids) throws Exception {
		List<Goods> goods = new ArrayList<>();//保存查询到了的数据信息
		for (int i = 0; i < ids.length; i++) {
			goods.add(getById(ids[i]));
		}
		return goods;
	}

	public List<Goods> findAll(int fistResult, int pageSize) throws Exception {
		
		List<String> strings = redisService.getPageList(fistResult, pageSize);
		List<Goods> goods = new ArrayList<>();
		for (String string : strings) {
			goods.add((Goods) redisService.getEntryByRedisString(string));
		}
		
		return goods;
		
	}
	
	
	/*public List<Goods> findAll(int fistResult, int pageSize) throws Exception {
		List<Goods> goodsList = new ArrayList<Goods>();
		Set<String> valueSet = (Set<String>)redisService.getList(tableName, 0, pageSize);
		if(valueSet != null){ //说明从缓存数据库中查询到了数据
             List<String> tmpList = new ArrayList<String>(valueSet);
             for (int i = 0; i < tmpList.size(); i++) {
				String objOfString = tmpList.get(i);
				Goods goods = (Goods)redisService.getEntryByRedisString(objOfString);
				goodsList.add(goods);
			}
            return goodsList;
		}else //没有查询到相应数据,从本地数据库中查询数据，并且还要保存到缓存数据库中
		{
			List<Goods> goodsListOfDB = baseDao.findAll(fistResult, pageSize);
			return goodsListOfDB;
		}
//		return baseDao.findAll(fistResult, pageSize);
	}*/



	public RedisServiceImpl redisService = new RedisServiceImpl() {
		
		
		@Override
		public String toString(Object model) {
			Goods goods =((Goods)model);
			return goods.getGoodsName()+";;;"//
					+goods.getPrice()+";;;"//
					+goods.getType()+";;;"//
					+goods.getId();
		}
		
		@Override
		public Object getEntryByRedisString(String redisString) {
			String[] ss = redisString.split(";;;");
			Goods goods = null;
			for (int i = 0; i < ss.length; i++) {
				if ("null".equals(ss[i])) {
					ss[i]=null;
				}
			}
			if (null != redisString) {
				goods = new Goods();
				goods.setGoodsName(ss[0]);
				goods.setPrice(Double.parseDouble(ss[1]));
				goods.setType(ss[2]);
				goods.setId(Integer.parseInt(ss[3]));
			}
			return goods;
		}

		@Override
		public List<String> getPageList(int firstResult, int offset) {
			List<String> strings = new ArrayList<>();
			for (int i = 0; i < offset; i++) {
				strings.add(getEntryString(tableName, (integers.get(firstResult+i)+"")));
			}
			return strings;
		}
		
		public void init() throws Exception {
			integers = baseDao.findIds();
		}
	};
	
	
	

}
