package test;

import java.io.File;
import java.util.List;

import struts.LoadStrutsXml;
import struts.PackageEntity;
import struts.StrutsFilter;

public class Test4Load {

	public static void main(String[] args) {
		String classPath = StrutsFilter.class.getResource("/").getPath();
		try {
			List<PackageEntity> packageEntityList = new LoadStrutsXml().readStrutsXml(
					new File(classPath,"struts.xml"));
			for (PackageEntity packageEntity : packageEntityList) {
				System.out.println(packageEntity);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
