package struts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;





import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LoadStrutsXml {
	
	PackageEntity packageEntity ;
	List<PackageEntity> packageEntities;
	SAXReader saxReader;
	Document document = null;
	/*public static void main(String[] args) {
		
		try {
			File file =new LoadStrutsXml().getFile("struts.xml");
			new LoadStrutsXml(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	public LoadStrutsXml(){
		packageEntities = new ArrayList<PackageEntity>();
		saxReader = new SAXReader();  
	}
	
	public List<PackageEntity> readStrutsXml(File inputXml) throws ClassNotFoundException {
		
		try {
			document = saxReader.read(inputXml);
		} catch (DocumentException e) {
			new RuntimeException("Struts.xml文件读取异常");
			e.printStackTrace();
		}
		// 获得文档的根节点
		Element root = document.getRootElement();
		// 遍历根节点下所有子节点
		 List<Element> listElement=root.elements();//所有一级子节点的list   
		 for(Element packagEle:listElement){//遍历所有一级子节点   
			packageEntity = new PackageEntity();
			System.out.println(packagEle.getName());
			
			//获取name和namespace
			String name = packagEle.attribute("name").getData().toString();
			String namespace = packagEle.attribute("namespace").getData().toString();
			System.out.println("namespace=="+namespace);
			/**
			 * <package name="weixin" namespace="org.jiumao.weixin.action">
			 * 设置package的属性值
			 * 设置id为name，name为namespace
			 */
			packageEntity.setId(name);
			packageEntity.setPackageName(namespace);
			
			
			//循环遍历所有子节点即action节点,并设置
			Iterator<?> iterator = packagEle.elementIterator();
			while (iterator.hasNext()) {
				Element actions = (Element)iterator.next();
				//获取name和class,method属性
				String actionName = actions.attribute("name").getData().toString();
				System.out.println("actionName=="+actionName);
				String className = actions.attribute("class").getData().toString();
				String methodName = actions.attribute("method").getData().toString();
				/**
				 * <action name="user_*"  class="UserAction" method="{1}">
				 * 设置action的属性值
				 * 设置name为actionName，class为className,method为methodName
				 */
				ActionEntity actionEntity = packageEntity.addActionEntitie(methodName, Class.forName(namespace+"."+className), actionName);
				
				//循环遍历所有子节点即action节点,并设置
				Iterator<?> iterator2 = actions.elementIterator();
				while (iterator2.hasNext()) {
					Element results = (Element) iterator2.next();
					//获取name和class,method属性
					String resultName = results.attribute("name").getData().toString();
					String resultValue = results.getTextTrim();
					System.out.println("resultName=="+resultName+"resultValue=="+resultValue);
					/**
					 * <result name="list" >/WEB-INF/jsp/userAction/list.jsp</result>
					 * 设置result的属性值
					 * 设置resultName为name，resultValue为值
					 */
					actionEntity.addResultEntitie(resultName, resultValue);
				}
				
			}
			
			
//			System.out.println(packageEntity.getActionEntities().get(0).getResultEntities().get(0).toString());
			
			packageEntities.add(packageEntity);
		}
		return packageEntities;

	}
	
	
}
