package base;

import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

import spring.SpringFactory;
import struts.ActionSupport;

public abstract class BaseAction<T extends BaseBean> extends ActionSupport {

	protected BaseService baseService;
	private Class<T> clazz;
	protected Logger logger;

	public BaseAction() {
		try {
			// 通过反射获取T的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			clazz = (Class<T>) pt.getActualTypeArguments()[0];

			// User -> userService
			String clazzSimpleName = clazz.getSimpleName();
			clazzSimpleName = clazzSimpleName.substring(0, 1).toLowerCase()
					+ clazzSimpleName.substring(1) + "Service";
			//记录日志
			logger = Logger.getLogger(clazz);
			logger.debug("clazzSimpleName =" + clazzSimpleName);
			baseService = (BaseService<T>) SpringFactory
					.getBean(clazzSimpleName);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
