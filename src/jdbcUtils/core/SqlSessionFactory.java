package jdbcUtils.core;
public class SqlSessionFactory extends SessionFactory {
	public SqlSessionFactory(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 设计模式的一种facade 外观设计模式
	 * @param url jdbc协议
	 * @param user 登录名
	 * @param password 密码
	 * @param driver 驱动
	 * @param initConnNums 初始化连接池连接数
	 * @param maxConnNums  最大连接数
	 * @return
	 */
	public static SessionFactory getSessionFactory(String url, String user,
			String password, String driver, int initConnNums, int maxConnNums) {
		JdbcConnect jdbc = new JdbcConnect(url, user, password, driver);
		DataSource dataSource = new DataSource(jdbc, initConnNums, maxConnNums);
		return new SqlSessionFactory(dataSource);
	}
	
	public static SessionFactory getSessionFactory(String url, String user,
			String password, String driver) {
		return getSessionFactory(url, user, password, driver,0,0);
	}
}
