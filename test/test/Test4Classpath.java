package test;

import struts.StrutsFilter;

public class Test4Classpath {

	public static void main(String[] args) {
		String string = StrutsFilter.class.getResource("/").getPath();
		System.out.println(string);
	}
}
