package test;

import java.lang.reflect.Method;

import org.jiumao.talentMarket.action.ActivityAction;

public class Test4ReturnType {
		public static void main(String[] args) {
			 Method[] methods=ActivityAction.class.getMethods();
			 for (Method method : methods) {
				System.out.println(method.getReturnType()+" || "+method.getName());
				System.out.println("leixing ="+"void".equals(method.getReturnType().toString()));
			}
		}
		
		
}

class Test4String {
	
	public static void main(String[] args) {//不能用||或者空格做分隔
		String[] s = ";;;;;;;;;;;;;;;;;;;;".split(";;");
		for (String string : s) {
			
			System.out.println(string);
		}
	}
	
}
