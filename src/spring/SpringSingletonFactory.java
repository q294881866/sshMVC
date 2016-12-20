package spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public class SpringSingletonFactory {
    private static  Resource cr = null; 
    private static XmlBeanFactory bf=null; 
	
  
    private SpringSingletonFactory() {  
    }  
  
    private static synchronized void syncInit() {  
        if (cr == null) {  
            cr = new ClassPathResource("applicationContext.xml");
            bf=new XmlBeanFactory(cr); 
        }  
    }  
  

	
	public static Object getBean(String beanName){
		if (bf == null) {  
            syncInit();  
        }  
		return bf.getBean(beanName);
	}
  
	
}
