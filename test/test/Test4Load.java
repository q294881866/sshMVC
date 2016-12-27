package test;

import java.io.File;
import java.util.List;

import struts.StrutsManager;
import struts.PackageEntity;
import struts.StrutsFilter;

public class Test4Load {

	public static void main(String[] args) {
		String classPath = StrutsFilter.class.getResource("/").getPath();

		System.out.println("classPath =" + classPath);
		try {
			StrutsManager.readStrutsXml(new File(classPath, "struts.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
