package org.jiumao.talentMarket.action;




import java.io.IOException;
import java.io.PrintWriter;

import org.jiumao.redis.domain.IdGeneration;
import org.jiumao.talentMarket.domain.ActivityPlanning;
import org.jiumao.talentMarket.service.ActivityPlanningService;

import base.BaseAction;

/**
 * 用户相关的请求
 * @author Administrator
 *			返回json字符串
 */
public class ActivityPlanningAction extends BaseAction<ActivityPlanning>{
	ActivityPlanningService baseService = (ActivityPlanningService)super.baseService;
	public ActivityPlanningAction() {
	}
	
	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		Integer activityId = new Integer(request.getParameter("activityId"));
		double minPrice = Double.parseDouble(request.getParameter("minPrice"));
		int maxDiscount = Integer.parseInt(request.getParameter("maxDiscount"));
		int minNums = Integer.parseInt(request.getParameter("minNums"));
		int maxNums = Integer.parseInt(request.getParameter("maxNums"));
		int discount = Integer.parseInt(request.getParameter("discount"));
		int step = Integer.parseInt(request.getParameter("step"));
		int isUseChildsDiscount = Integer.parseInt(request.getParameter("isUseChildsDiscount"));
		baseService.save(IdGeneration.getIdByString(IdGeneration.ActivityPlanningId),minPrice,maxDiscount,
				minNums,maxNums,discount,null,isUseChildsDiscount,step,activityId,null);
		if (1==isUseChildsDiscount) {//如果设置子商品规则
			//设置活动策划表
			request.getSession().setAttribute("activityPlanningId", 1);
			return "addChildrenPlanningList";
		}else {
			return "list";
		}
	}
	
	/**
	 * 添加子商品活动规则
	 * @return
	 * @throws Exception 
	 */
	public void addChildrenPlanning() throws Exception{
		Integer activityPlanningId = Integer.parseInt(request.getParameter("activityPlanningId"));
		Integer goodsId = new Integer(request.getParameter("goodsId"));
		System.out.println("goodsId="+goodsId);
		double minPrice = Double.parseDouble(request.getParameter("minPrice"));
		int maxDiscount = Integer.parseInt(request.getParameter("maxDiscount"));
		int minNums = Integer.parseInt(request.getParameter("minNums"));
		int maxNums = Integer.parseInt(request.getParameter("maxNums"));
		int discount = Integer.parseInt(request.getParameter("discount"));
		int step = Integer.parseInt(request.getParameter("step"));
		int isUseChildsDiscount = Integer.parseInt(request.getParameter("isUseChildsDiscount"));
		baseService.save(IdGeneration.getIdByString(IdGeneration.ActivityPlanningId),minPrice,maxDiscount,
				minNums,maxNums,discount,activityPlanningId,isUseChildsDiscount,step,null,goodsId);
		
	}
	
	public String addChildrenPlanningUI(){
		
		return "addChildrenPlanningUI";
	}
	
	
	
	
	
	
	
}
