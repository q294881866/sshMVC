package base;

import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

import spring.SpringFactory;
import struts.ActionSupport;


public abstract class BaseAction<T extends BaseBean> extends ActionSupport{

	// =============== ModelDriven的支持 ==================

	protected T model;
	protected BaseService baseService;
	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
			
			String clazzSimpleName = clazz.getSimpleName();
			clazzSimpleName = clazzSimpleName.substring(0, 1).toLowerCase()+clazzSimpleName.substring(1)+"Service";
			Logger logger = Logger.getLogger(BaseAction.class);
			logger.debug("clazzSimpleName ="+clazzSimpleName);
			baseService = (BaseService<T>) SpringFactory.getBean(clazzSimpleName);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================
	//系统管理Service声明

	// ============== 分页用的参数 =============

	protected int pageNum = 1; // 当前页
	protected int pageSize = 10; // 每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
