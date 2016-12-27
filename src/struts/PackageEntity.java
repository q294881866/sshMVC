package struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于保存Struts.xml每个package数据
 * 
 * @author Administrator
 * 
 */
public class PackageEntity {

	private String id;// 包唯一命名对应name
	private String packageName;// 包名，对应class
	private Map<String, ActionEntity> actionEntities = new HashMap<>();// Package的子属性

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * 添加一个action实体
	 * 
	 * @param method
	 * @param actionClass
	 * @param actionName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public ActionEntity addAction(String method, String className,
			String actionName) throws ClassNotFoundException {
		ActionEntity a = new ActionEntity(actionName,
				Class.forName(this.packageName + "." + className), method);
		this.actionEntities.put(actionName, a);
		return a;
	}

	/**
	 * 获取一个Action
	 * 
	 * @param key
	 * @return
	 */
	public ActionEntity getAction(String key) {
		return this.actionEntities.get(key);
	}

	public ActionEntity delAction(String key) {
		return this.actionEntities.remove(key);
	}

}

class ActionEntity {
	private String actionName;// action 中name
	private Class actionClass;// action 中class
	private String method;// action 中method
	private Map<String,ResultEntity> resultEntities = new HashMap<>();// action中子属性result

	public ActionEntity(String actionName, Class actionClass, String method) {
		super();
		this.actionName = actionName;
		this.actionClass = actionClass;
		this.method = method;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Class getActionClass() {
		return actionClass;
	}

	public void setActionClass(Class actionClass) {
		this.actionClass = actionClass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ResultEntity getResult (String key) {
		return resultEntities.get(key);
	}

	public ResultEntity addResult(String resultName, String resultValue) {
		return resultEntities.put(resultName,new ResultEntity(resultName, resultValue));
	}
	
	public ResultEntity delResult(String resultName) {
		return resultEntities.remove(resultName);
	}
}

class ResultEntity {
	private String resultName;// result 中name属性
	private String resultValue;// 跳转到的值
	public ResultEntity(String resultName, String resultValue) {
		super();
		this.resultName = resultName;
		this.resultValue = resultValue;
	}

	@Override
	public String toString() {
		return resultName + "  " + resultValue;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
}