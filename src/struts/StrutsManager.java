package struts;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 加载Struts配置文件<br>
 * 返回一个配置文件解析实例
 */
public class StrutsManager {

	private Map<String, PackageEntity> packageEntities;
	private SAXReader saxReader;// dom4j xml解析
	private Document document;

	private StrutsManager(File inputXml) throws DocumentException,
			ClassNotFoundException {
		packageEntities = new HashMap<String, PackageEntity>();
		saxReader = new SAXReader();// io流转document结构
		document = saxReader.read(inputXml);

		parse();// 从document中解析成需要的java数据结构
	}

	/**
	 * StrutsManager工厂根据配置文件生成实例
	 * @param inputXml
	 * @return
	 * @throws ClassNotFoundException
	 * @throws DocumentException
	 */
	public static StrutsManager readStrutsXml(File inputXml)
			throws ClassNotFoundException, DocumentException {
		return new StrutsManager(inputXml);
	}

	/**
	 * 层次遍历Document到特定的数据结构
	 * @throws ClassNotFoundException
	 */
	private void parse() throws ClassNotFoundException {
		// 获取xml的所有package节点
		List<Element> packages = document.getRootElement().elements();
		PackageEntity p;
		// 1.package
		for (Element packageEle : packages) {// 遍历所有一级子节点
			p = loadPackage(packageEle);
			// 2.action
			Iterator<?> actions = packageEle.elementIterator();
			while (actions.hasNext()) {
				Element action = (Element) actions.next();
				ActionEntity a = loadAction(p, action);
				// 3.result
				Iterator<Element> results = action.elementIterator();
				while (results.hasNext()) {
					Element result = results.next();
					loadResult(a, result);
				}
			}
			packageEntities.put(p.getId(), p);
		}
	}

	/**
	 * action中添加result节点
	 * @param a
	 *            action
	 * @param result
	 *            Document中result节点
	 */
	public void loadResult(ActionEntity a, Element result) {
		String name = getAttrValue(result, "name");
		String value = result.getTextTrim();
		a.addResult(name, value);
	}

	/**
	 * 获取所有action节点,并赋值ActionEntity <br>
	 * 1. <code> action name="user_*" class="UserAction" method="{1}" </code><br>
	 * 2. name:actionName，class:className，method:methodName
	 * 
	 * @throws ClassNotFoundException
	 */
	private ActionEntity loadAction(PackageEntity p, Element action)
			throws ClassNotFoundException {
		// 获取name和class,method属性
		String actionName = getAttrValue(action, "name");
		String className = getAttrValue(action, "class");
		String methodName = getAttrValue(action, "method");
		return p.addAction(methodName, className, actionName);
	}

	/**
	 * 获取包下的name和namespace属性 <package name="weixin"
	 * namespace="org.jiumao.weixin.action"> 设置package的属性值
	 * 设置id为name，name为namespace
	 */
	private PackageEntity loadPackage(Element e) {
		PackageEntity p = new PackageEntity();
		String name = getAttrValue(e, "name");
		String namespace = getAttrValue(e, "namespace");
		p.setId(name);
		p.setPackageName(namespace);
		return p;
	}

	/**
	 * 上线一个模块 
	 */
	public PackageEntity addPackage(PackageEntity p) {
		synchronized (packageEntities) {
			return this.packageEntities.put(p.getId(), p);
		}
	}
	
	/**
	 * 下线一个模块 
	 */
	public PackageEntity delPackage(PackageEntity p) {
		synchronized (packageEntities) {
			return this.packageEntities.remove(p.getId());
		}
	}

	/**
	 * 获取文档节点属性的值
	 * @param e	    节点元素
	 * @param key 节点属性名
 	 * @return
	 */
	private String getAttrValue(Element e, String key) {
		if (null == e)
			return null;
		return e.attribute(key).getData().toString();
	}

	public PackageEntity getPackage(String key) {
		return packageEntities.get(key);
	}

}
