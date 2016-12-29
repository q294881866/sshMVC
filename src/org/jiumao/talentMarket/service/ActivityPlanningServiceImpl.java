package org.jiumao.talentMarket.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jiumao.talentMarket.dao.ActivityDao;
import org.jiumao.talentMarket.dao.ActivityPlanningDao;
import org.jiumao.talentMarket.dao.GoodsDao;
import org.jiumao.talentMarket.domain.ActivityPlanning;

import spring.SpringFactory;
import base.BaseServiceImpl;

public class ActivityPlanningServiceImpl extends BaseServiceImpl<ActivityPlanning> implements ActivityPlanningService {

	GoodsDao goodsDao = (GoodsDao)SpringFactory.getBean("goodsDao");
	ActivityDao activityDao = (ActivityDao)SpringFactory.getBean("activityDao");
	ActivityPlanningDao dao = (ActivityPlanningDao)baseDao;
	public ActivityPlanning getActivityPlanningByActivityId(Integer id) {
		return dao.getActivityPlanningByActivityId(id);
	}


	/**
	 * 
	 * @param goodsIds
	 * 			//从页面订单获取商品id信息
	 * @param goodsNum
	 * 			//从页面订单获取商品数量信息
	 * @param activityId
	 * 			//从页面activityId属性获取
	 * @return
	 */
	 public double post(String[] goodsIds,String[] goodsNum ,Integer activityId){
    	 //1.用户提交订单信息
		 double sumPrice = 0;// 这个值代表这个套餐里面的商品总价格
    	 //2.查询此活动的策划规则信息（从数据库通过活动id）
    	 ActivityPlanning activityPlanning = dao.getActivityPlanningByActivityId(activityId);
    	 
    	 //3.是否有子商品活动规则
		if (activityPlanning.isUseChildsPlanning()){
			// 这个方法需要实现 查询子商品的规则
			List<ActivityPlanning> activityPlannings = dao.getChildsByParentId(activityPlanning.getId());
			//key 商品id，value 子商品活动规则
			Map<Integer, ActivityPlanning> goodsId2ActivityPlan = new HashMap<Integer, ActivityPlanning>();
			//把商品id 对应的规则放入map中
			for (ActivityPlanning activityPlanning2 : activityPlannings) {
				goodsId2ActivityPlan.put(activityPlanning2.getGoodsId(), activityPlanning2);
			}
			//循环匹配子商品和活动规则
			
			for (int i = 0; i < goodsNum.length; i++) {
				 sumPrice+=computer(Integer.parseInt(goodsNum[i]),goodsId2ActivityPlan.get(Integer.parseInt(goodsIds[i])));
			}
		}else {
			try {
				return activityDao.getById(activityId).getPrice();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sumPrice;
     }

     
     /**
      * 
      * @param 商品数量
      * 		用户购买套餐中某个商品的数量
      * @param activityPlanning
      * 		此活动中此商品对应的规则
      * @return
      */
	private double computer(int number,ActivityPlanning activityPlanning) {
		double sumPrice = 0;// 这个值代表这个套餐里面的字商品价格

		//判断是否符合条件
		if (number > activityPlanning.getMinNums()// 判断最低数量达到标准
				&&number < activityPlanning.getMaxNums()){// 判断最大数量达到标准
			//计算价格
			//1.通过商品id查询价格
			try {
				sumPrice = goodsDao.getById(activityPlanning.getGoodsId()).getPrice();
				System.out.println(sumPrice+"==sumPrice");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2.计算折扣 商品折扣信息，按起始折扣-每买一个所打折扣
			float discount = activityPlanning.getDiscount()-(number-1)*activityPlanning.getStep();
			if (discount<activityPlanning.getMaxDiscount()) {//如果折扣低于最大折扣，赋值为最大折扣
				discount = activityPlanning.getMaxDiscount();
			}
			sumPrice =sumPrice*number*discount;
			if (sumPrice > activityPlanning.getMinPrice()){// 判断最低价格达到标准
				return sumPrice;
			}
			
		}
		return 0;
	}


}
