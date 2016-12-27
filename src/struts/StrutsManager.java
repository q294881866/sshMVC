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
 * 
 * @author ppf@jiumao.org
 * @date 2016年12月26日
 */
public class StrutsManager {

	private Map<String, PackageEntity> packageEntities;
	private SAXReader saxReader;
	private Document document;

	private StrutsManager(File inputXml) throws DocumentException,
			ClassNotFoundException {
		packageEntities = new HashMap<String, PackageEntity>();
		saxReader = new SAXReader();
		document = saxReader.read(inputXml);

		parse();
	}

	/**
	 * 读取配置文件，返回一个
	 * 
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
	 * 动态添加一个模块
	 * 
	 * @param p
	 */
	public void addPackage(PackageEntity p) {
		synchronized (packageEntities) {
			this.packageEntities.put(p.getId(), p);
		}
	}

	/**
	 * 解析配置文件到特定的数据结构
	 * 
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

				ActionEntity actionEntity = loadAction(p, action);

				// 3.result
				Iterator<Element> results = action.elementIterator();
				while (results.hasNext()) {
					Element result = results.next();
					// 获取name和class,method属性
					String resultName = getAttrValue(result, "name");
					String resultValue = result.getTextTrim();
					actionEntity.addResult(resultName, resultValue);
				}

			}
			packageEntities.put(p.getId(), p);
		}
	}

	/**
	 * 获取所有action节点,并赋值ActionEntity <br>
	 * 1. {@code action name="user_*" class="UserAction" method=" 1}" <br>
	 * 2. name:actionName，class:className,method:methodName
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

	private String getAttrValue(Element e, String key) {
		if (null == e)
			return null;
		return e.attribute(key).getData().toString();
	}

	@SuppressWarnings("unchecked")
	public String invoke(StringBuffer url, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// 3.获取请求的action，如/weixin/user_test_list，获取weixin和user_test_list
		String[] urltemp = url.toString().split("/");
		int last = urltemp.length - 1;
		String id = urltemp[last - 1];// weixin
		String[] tmp = urltemp[last].split("_");// user_test_list
		String methodName = tmp[tmp.length - 1];// list
		String action = urltemp[last].replace(methodName, "") + "*";// user_test_*

		ActionEntity a = packageEntities.get(id).getAction(action);
		Class<ActionSupport> atctionClass = a.getActionClass();

		ActionSupport actionSupport = atctionClass.newInstance();
		// 传入request、response
		actionSupport.setRequestAndResponse(req, res);

		Method m = atctionClass.getMethod(methodName, null);
		// 如果返回值类型是String执行返回结果
		if (String.class.isAssignableFrom(m.getReturnType())) {
			String res1 = (String) m.invoke(actionSupport, null);
			return a.getResult(res1).getResultValue();
		} else if ("void".equals(m.getReturnType().toString())) {
			m.invoke(actionSupport, null);
			return "void";
		}
		return null;
	}

}
