package spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringFactory {
    private static  Resource cr = null; //Spring提供加载各种文件资源的统一接口
    private static XmlBeanFactory bf=null; //Spring提供的xml解析类
	
  
    private SpringFactory() {  
    }  
  
    private static synchronized void syncInit() {  
        if (cr == null) { //二次判断
        	//spring 以类路径的方式访问配置文件
            cr = new ClassPathResource("applicationContext.xml");
            //根据XML文件，生成类对象生成工厂
            bf=new XmlBeanFactory(cr); 
        }  
    }  
  

	
	public static Object getBean(String beanName){
		//多个线程可能同时访问这里，排队初始化，所有需要二次判断
		if (bf == null) {  
            syncInit();  //执行线程同步初始化，并做二次判断
        } 
		//根据类的别名获取，类的实例化对象
		return bf.getBean(beanName);
	}
}
