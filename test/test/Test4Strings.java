package test;

import org.jiumao.talentMarket.domain.Goods;

public class Test4Strings {

	public static void main(String[] args) {
		Goods goods = new Goods();
		goods.setGoodsName("null");
		System.out.println(null==goods.getGoodsName());
		String s = goods.getGoodsName() + ";;;"//
				+ goods.getPrice() + ";;;"//
				+ goods.getType() + ";;;"//
				+ goods.getId();
		String[] ss = s.split(";;;");
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]==null);
		}
		
		Object[] ss2 = s.split(";;;");
		for (int i = 0; i < ss2.length; i++) {
			System.out.println(ss2[i]);
		}
	}
}
