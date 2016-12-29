package base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jiumao.talentMarket.domain.ApproveSql;
import org.jiumao.talentMarket.domain.EmployeeSql;

import jdbcUtils.DBUtil;
import jdbcUtils.core.MyJdbc;
import jdbcUtils.core.MySqlSessionFactory;
import jdbcUtils.core.Session;
import jdbcUtils.core.SessionFactory;

@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T extends BaseBean> implements DaoSupport<T> {

	/**
	 * MySQL写服务器连接工程
	 */
	protected static SessionFactory mySqlWriterSessionFactory = MySqlSessionFactory
			.getSessionFactory("jdbc:mysql://127.0.0.1:3306/test", "root",
					"", "com.mysql.jdbc.Driver");
	protected Session session;

	private Class<T> clazz;
	private Class<?> SqlClazz ; 
	protected T model;
	int resultset = -1;//设置数据库更新为失败
	
	protected Session getSession(){
		if (null==session) {
			session = mySqlWriterSessionFactory.getSession();
		}
		return session;
	}

	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz ---> " + clazz);
		try {
			System.err.println(clazz.toString().replace("class ", ""));
			SqlClazz = Class.forName(clazz.toString().replace("class ", "")+"Sql");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getSession();
	}

	public int save(Object... parameters) throws Exception {
		Field field = SqlClazz.getField("save");
		System.out.println((String)field.get(SqlClazz));
		return getSession().update((String)field.get(SqlClazz),parameters);
	}

	public int update(String sql,Object... parameters) throws Exception {
		return getSession().update(sql, parameters);
	}

	public int delete(Integer id) throws Exception {
		Field field = SqlClazz.getField("deleteById");
		System.out.println((String)field.get(SqlClazz));
		return getSession().update((String)field.get(SqlClazz), id);
	}

	public T getById(Integer id) throws Exception {
		Field field = SqlClazz.getField("getById");
		System.out.println((String)field.get(SqlClazz));
		model = (T) getSession().getObject((String)field.get(SqlClazz), clazz, id);
		
		return model;
	}

	public List getByIds(Integer[] ids) throws Exception {
		Field field = SqlClazz.getField("getByIds");
		System.out.println((String)field.get(SqlClazz));
		return getSession().getObjects((String)field.get(SqlClazz), clazz, ids);
	}

	public List findAll(int fistResult, int pageSize) throws Exception {
		Field field = SqlClazz.getField("findAll");
		System.err.println(field);
		List<Object> list =getSession().getObjects((String)field.get(SqlClazz), clazz, fistResult,pageSize);
		return list;
	}

	@Override
	public int updateById(Integer id, Object... parameters) throws Exception {
		Connection con = getSession().getConn();
		Field field = SqlClazz.getField("updateById");
		System.out.println((String) field.get(SqlClazz));
		PreparedStatement ps = con.prepareStatement((String) field
				.get(SqlClazz));
		for (int i = 0; i < parameters.length; i++) {
			ps.setObject(i + 1, parameters[i]);
		}
		ps.setInt(1, id);
		int rs = ps.executeUpdate();
		// 关闭资源
		getSession().free(null, ps);
		return rs;
	}

	@Override
	public List<Integer> findAllId() throws Exception {
		List<Integer> integers = new ArrayList<>();
		Connection con = getSession().getConn();
		Field field = SqlClazz.getField("findAllId");
		System.out.println((String) field.get(SqlClazz));
		PreparedStatement ps = con.prepareStatement((String) field
				.get(SqlClazz));
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			integers.add(rs.getInt(1));
			
		}
		// 关闭资源
		getSession().free(rs, ps);
		return integers;
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		mySqlWriterSessionFactory.close(session);
	}


}
