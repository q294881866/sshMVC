package jdbcUtils.core;

/**
 * 桥接模式
 * 对应不同的数据源提供不同的数据库连接
 */

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 数据库会话操作<br>
 * 连接池管理。处理每个会话请求
 */
public abstract class SessionFactory {
	private DataSource dataSource;//数据库连接池
	//每个线程维护的变量。线程内共享。不同线程不影响
	private ThreadLocal<Connection> thread = new ThreadLocal<Connection>();
	
	Logger logger = Logger.getLogger(SessionFactory.class);
	public SessionFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		try {
			//先从当前线程中取数据
			Connection con = thread.get();
			if(con==null){
				con = dataSource.getConnection();
				thread.set(con);
			}
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Session getSession(){
		return new Session(getConnection());
	}
	
	public void free(Connection conn){
		this.dataSource.free(conn);
	}
	
	public void close(Session s){
		if (null!=s.getConn()) {
			this.free(s.getConn());
			s.close();
		}
	}

}
