package spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringFactory {
	private Resource cr; // Spring提供加载各种文件资源的统一接口
	private XmlBeanFactory bf; // Spring提供的xml解析类
	private static final SpringFactory INSTANCE = new SpringFactory();;

	private SpringFactory() {
		// spring 以类路径的方式访问配置文件
		cr = new ClassPathResource("applicationContext.xml");
		// 根据XML文件，生成类对象生成工厂
		bf = new XmlBeanFactory(cr);
	}

	public static Object getBean(String beanName) {
		// 根据类的别名获取，类的实例化对象
		return INSTANCE.bf.getBean(beanName);
	}
}
