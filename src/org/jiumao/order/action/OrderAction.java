package org.jiumao.order.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jiumao.order.domin.Order;
import org.jiumao.order.domin.OrderDetail;
import org.jiumao.talentMarket.domain.Goods;
import org.jiumao.talentMarket.service.GoodsService;

import spring.SpringFactory;
import base.BaseAction;

public class OrderAction extends BaseAction<Order> {
	GoodsService goodsService = (GoodsService) SpringFactory
			.getBean("goodsService");

	public OrderAction() {

	}

	public String list() {
		try {
			List list = baseService.findAll(0, 20);
			request.setAttribute("orderList", list);
			System.out.println("orderList" + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String findById() throws Exception{
		String id = request.getParameter("id");
		model=(Order) baseService.getById(Integer.parseInt(id));
		request.setAttribute("order", model);
		List<OrderDetail> orderDetails = new ArrayList<>();
		String[] goodsIds = model.getGoodsIds().split(";");
		String[] goodsNames = model.getGoodsName().split(";");
		String[] goodsNumbers = model.getGoodsNumbers().split(";");
		String[] goodsPrices = model.getGoodsPrice().split(";");
		OrderDetail detail;
		for (int i = 0; i < goodsPrices.length; i++) {
			detail = new OrderDetail();
			detail.setGoodsId(Integer.parseInt(goodsIds[i]));
			detail.setGoodsName(goodsNames[i]);
			detail.setGoodsNumber(goodsNumbers[i]);
			detail.setGoodsPrice(goodsPrices[i]);
		}
		request.setAttribute("orderDetails", orderDetails);
		return "detail";
	}

	public void deleteById() throws Exception {
		String id=request.getParameter("id");
	    int i = baseService.delete(Integer.parseInt(id));
	    PrintWriter out = response.getWriter();
	    if (1>i) {
	    	out.print("<font color='red' size='35'>订单取消失败</font>");
		}else {
			out.print("<font color='blue' size='35'>订单取消成功</font>");
			
		}
	    out.close();
	}

}
