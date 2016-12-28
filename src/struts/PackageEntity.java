package struts;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于保存Struts.xml每个package数据
 */
public class PackageEntity {

	private String id;// 包唯一命名对应name
	private String packageName;// 包名，对应namespace
	// Package的 action节点。
	private Map<String/*action name*/, ActionEntity> actions = new HashMap<>();

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

	public ActionEntity addAction(String method, String className,
			String actionName) throws ClassNotFoundException {
		ActionEntity a = new ActionEntity(actionName,
				Class.forName(this.packageName + "." + className), method);
		this.actions.put(actionName, a);
		return a;
	}

	public ActionEntity getAction(String key) {
		return this.actions.get(key);
	}

	public ActionEntity delAction(String key) {
		return this.actions.remove(key);
	}

}

/**
 * action节点，包含多个result节点
 */
class ActionEntity {
	private String actionName;// action 中name
	private Class actionClass;// action 中class
	private String method;// action 中method
	// action中子属性result
	private Map<String/*result name*/,ResultEntity> results = new HashMap<>();

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
		return results.get(key);
	}

	public ResultEntity addResult(String resultName, String resultValue) {
		return results.put(resultName,new ResultEntity(resultName, resultValue));
	}
	
	public ResultEntity delResult(String resultName) {
		return results.remove(resultName);
	}
}

/**
 * result节点描述
 */
class ResultEntity {
	private String name;// result 中name属性
	private String value;// 跳转到的值
	public ResultEntity(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return name + "  " + value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}