package base;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import spring.SpringFactory;
public abstract class BaseServiceImpl<T extends BaseBean> implements BaseService<T>{
	private Class<T> clazz;
	protected DaoSupport<T> baseDao;
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz ---> " + clazz);
		//从spring beanfactory中获取bean
		String clazzSimpleName = clazz.getSimpleName();
		clazzSimpleName = clazzSimpleName.substring(0, 1).toLowerCase()+clazzSimpleName.substring(1)+"Dao";
		 baseDao = (DaoSupport) SpringFactory.getBean(clazzSimpleName);
	}
	
	public int save(Object... parameters) throws Exception {
		return baseDao.save(parameters);
	}
	public int delete(Integer id) throws Exception {
		return baseDao.delete(id);
	}
	public int update(String sql,Object... parameters) throws Exception {
		return baseDao.update(sql,parameters);
	}
	public T getById(Integer id) throws Exception {
		return baseDao.getById(id);
	}
	public List<T> getByIds(Integer[] ids) throws Exception {
		return baseDao.getByIds(ids);
	}
	public List<T> findAll(int fistResult, int pageSize) throws Exception {
		return baseDao.findAll(fistResult, pageSize);
	}
	@Override
	public int updateById(Integer id, Object... parameters) throws Exception {
		return baseDao.updateById(id, parameters);
	}
}
