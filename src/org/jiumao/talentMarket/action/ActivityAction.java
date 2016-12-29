package org.jiumao.talentMarket.action;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jdbcUtils.IdGenerator;

import org.apache.log4j.Logger;
import org.jiumao.redis.domain.IdGeneration;
import org.jiumao.talentMarket.domain.Activity;
import org.jiumao.talentMarket.domain.ActivityPlanning;
import org.jiumao.talentMarket.domain.Goods;
import org.jiumao.talentMarket.service.ActivityPlanningService;
import org.jiumao.talentMarket.service.ActivityService;
import org.jiumao.talentMarket.service.GoodsService;

import spring.SpringFactory;
import base.BaseAction;

/**
 * 用户相关的请求
 * @author Administrator
 *			返回json字符串
 */
public class ActivityAction extends BaseAction<Activity>{
	GoodsService goodsService = (GoodsService)SpringFactory.getBean("goodsService");
	ActivityPlanningService  activityPlanningService= (ActivityPlanningService)SpringFactory.getBean("activityPlanningService");
	ActivityService baseService = (ActivityService)super.baseService;
	
	Logger logger = Logger.getLogger(ActivityAction.class);
	
	public ActivityAction() {
		System.out.println("i am ActivityAction");
	}
	
	public String addUI(){
		try {
			 List<Goods> goodsList= goodsService.findAll(0, 8);
			 System.out.println("goodsList ="+goodsList);
			 request.setAttribute("goodsList", goodsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addUI";
	}
	
	/**
	 * 获取所有的活动列表，不管通过审核没有
	 * @return
	 */
	public String list(){
		try {
			List list = baseService.findAll(0, 20);
			request.setAttribute("activityList", list);
			System.out.println("activityList"+list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	public String list4Shop(){
		try {
			List list = baseService.findShopList(0, 20);
			request.setAttribute("activityList", list);
			System.out.println("activityList"+list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list4Shop";
	}
	
	
	public String add() {
		String activityName = request.getParameter("activityName");
		String activityPlanPeople = request.getParameter("activityPlanPeople");
		System.out.println("activityName="+activityName);
		String[] goods = request.getParameterValues("goods");
		String goodsIds="";
		//吧商品id用;号拼接
		for ( String string : goods) {
			goodsIds = goodsIds+string+";";
		}
		System.out.println(goodsIds);
		try {
			baseService.save(IdGeneration.getIdByString(IdGeneration.ActivityId),activityName,goodsIds,activityPlanPeople,0);
		
			//跳转到活动规则制定页面，准备数据
			request.setAttribute("activityName", activityName);
			request.setAttribute("id", 1);
			List<Goods> list = new ArrayList<>();
			for ( String integer : goods) {
				list.add(goodsService.getById(Integer.parseInt(integer)));
			}
		
		request.getSession().setAttribute("goodsList", list);
		//FIXME activityId没有设置
		request.getSession().setAttribute("activityId", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addPlanningUI";
	}
	
	public String detail() throws Exception{
		Integer id =Integer.parseInt( request.getParameter("id") );
		
		Activity model = (Activity) baseService.getById(id);
		request.setAttribute("model", model);
		String[] goods = model.getGoodsIds().split(";");
		List<Goods> list = new ArrayList<>();
		for ( String integer : goods) {
			list.add(goodsService.getById(Integer.parseInt(integer)));
		}
		System.out.println("goodsList"+list);
		request.setAttribute("goodsList", list);
		return "detail";
	}
	
	public void buy() throws IOException{
		Integer id = Integer.parseInt(request.getParameter("activityId"));
//		String[] prices = request.getParameterValues("price");这个不可信可能会模拟请求
		String[] numbers = request.getParameterValues("number");
		String[] goodsIds = request.getParameterValues("goods");
		double totalPrice = 0;//总价
		
		PrintWriter out = response.getWriter();
		
		logger.info("goodsIds =="+goodsIds.toString()+" numbers =="+numbers.toString());
		totalPrice=activityPlanningService.post(goodsIds, numbers, id);
		if (0 == totalPrice) {
			out.print("msg 请求有误");
		}else {
			out.print("msg 总价："+totalPrice+"元；");
		}
		out.close();
	}
	
	public String  postActivity(){
		Integer activityId = Integer.parseInt(request.getParameter("activityId"));
		 baseService.setHandler(activityId);
		list();
		return "list";
	}
	
	public String addPlanningUI(){
		
		return "addPlanningUI";
	}
	
	
	
	
}
