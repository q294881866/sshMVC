package jdbcUtils.core;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Session {

	private Connection conn;//当前使用的连接
	
	Logger logger = Logger.getLogger(Session.class);
	
	public Session(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 开启事务 并设置事务隔离级别 level one of the following Connection constants:
	 * Connection.TRANSACTION_READ_UNCOMMITTED,
	 * Connection.TRANSACTION_READ_COMMITTED,
	 * Connection.TRANSACTION_REPEATABLE_READ, or
	 * Connection.TRANSACTION_SERIALIZABLE. (Note that
	 * Connection.TRANSACTION_NONE cannot be used because it specifies that
	 * transactions are not supported.)
	 * 
	 * @param level
	 */
	public void startTransaction(int level) {
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(level);
		} catch (SQLException e) {
			new DaoException("事务开启时异常", e);
		}
	}

	/**
	 * 提交事务
	 */
	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			new DaoException("事务提交异常", e);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public List<Object> getObjects(String sql, Class clazz,Object... parameters)
			throws  Exception {
		if (null == sql) {
			throw new RuntimeException("sql is null");
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = conn.prepareStatement(sql);
		
		if (null!=parameters) {
			logger.debug("parameters.length=="+parameters.length);
			//设置参数
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i+1, parameters[i]);
			}
		}
		rs = ps.executeQuery();

		List<Object> objects = new ArrayList<Object>();
		Method[] ms = clazz.getMethods();
		while (rs.next()) {
			objects.add(getBean(clazz, rs));
		}
		return objects;
	}

	private Object getBean(Class clazz, ResultSet rs)
			throws IntrospectionException, InstantiationException,
			IllegalAccessException, SQLException, InvocationTargetException {
		if (null == clazz&&null == rs) {
			return null;
		}
		String[] colNames = getColNames(rs);
		
		Object object = clazz.newInstance();
		
			for (int i = 0; i < colNames.length; i++) {
				String colName = colNames[i];
				logger.debug("colName =="+colName);
				PropertyDescriptor pd2 = new PropertyDescriptor(colName,clazz);
				Method methodSetX = pd2.getWriteMethod();
				System.err.println(colName+"="+rs.getObject(colName));
				methodSetX.invoke(object,rs.getObject(colName));
			}
		return object;
	}

	private String[] getColNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		String[] colNames = new String[count];
		for (int i = 1; i <= count; i++) {
			colNames[i - 1] = rsmd.getColumnLabel(i);
		}
		return colNames;
	}

	public Object getObject(String sql, Class clazz,Object... parameters) throws SQLException,
			Exception, IllegalAccessException, InvocationTargetException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = conn.prepareStatement(sql);
		if (null!=parameters) {
			//设置参数
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i+1, parameters[i]);
			}
		}
		rs = ps.executeQuery();
		if (rs.next()){
			return getBean(clazz, rs);
		}
			return null;
	}

	public int update(String sql,Object... parameters) throws Exception{
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		if (null!=parameters) {
			//设置参数
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i+1, parameters[i]);
			}
		}
		return ps.executeUpdate();
	}

	public Connection getConn() {
		return conn;
	}
	
	public void free(ResultSet rs, Statement st){
		MyJdbc.free(rs, st,null);
	}
	
}
