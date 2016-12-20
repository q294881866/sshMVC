package struts;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于保存Struts.xml每个package数据
 * @author Administrator
 *
 */
public class PackageEntity {

	private String id;//包唯一命名对应name
	private String packageName;//包名，对应class
	private List<ActionEntity> actionEntities=new ArrayList<ActionEntity>();//Package的子属性
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
	public List<ActionEntity> getActionEntities() {
		return actionEntities;
	}
	/**
	 * 添加一个action实体
	 * @param method
	 * @param actionClass
	 * @param actionName
	 * @return 
	 */
	public ActionEntity addActionEntitie(String method, Class actionClass, String actionName) {
		ActionEntity actionEntity= new ActionEntity(actionName, actionClass, method);
		this.actionEntities.add(actionEntity);
		return actionEntity;
	}
	
}


class ActionEntity{
	private String actionName;//action 中name
	private Class actionClass;//action 中class
	private String method;//action 中method
	private List<ResultEntity> resultEntities = new ArrayList<ResultEntity>();//action中子属性result
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
	public List<ResultEntity> getResultEntities() {
		return resultEntities;
	}
	/**
	 * 添加一个result实体
	 * @param resultName
	 * @param resultValue
	 */
	public void addResultEntitie(String resultName, String resultValue) {
		this.resultEntities.add(new ResultEntity(resultName, resultValue));
	}
}


class ResultEntity{
	private String resultName;//result 中name属性
	private String resultValue;//跳转到的值
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
	public ResultEntity(String resultName, String resultValue) {
		super();
		this.resultName = resultName;
		this.resultValue = resultValue;
	}
	
	@Override
	public String toString() {
		
		return resultName+"  "+resultValue;
	}
	
}