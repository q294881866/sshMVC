package jdbcUtils.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 介绍建造者模式 数据库连接池实习
 * 
 * @author Administrator
 * 
 */
public class DataSource {

	private static int initCount = 1;
	private static int maxCount = 1 << 6;
	int currentCount = 0;
	private MyJdbc jdbc;

	// 数据库连接池<>表示泛型Connection表示只存Connection及子类这里报错了 类型不能转换
	// LinkedList<Connection> connectionsPool = (LinkedList<Connection>)
	// Collections.synchronizedList(new LinkedList<Connection>());
	LinkedList<Connection> connectionsPool = new LinkedList<Connection>();

	private DataSource() {

	}

	public DataSource(MyJdbc jdbc) {
		this.jdbc = jdbc;
		try {
			for (int i = 0; i < initCount; i++) {
				System.err.println("初始化了" + i + "条连接");
				this.connectionsPool.addLast(jdbc.getConnection());
				this.currentCount++;
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public Connection getConnection() throws SQLException {
		// synchronized (connectionsPool) {//没必要用并发访问
		if (this.connectionsPool.size() > 0)
			return this.connectionsPool.removeFirst();

		/*
		 * 如果当前没有连接了
		 */
		// 新建连接
		if (this.currentCount < maxCount) {
			this.currentCount++;
			return jdbc.getConnection();
		}
		// 线程休眠
		// 1.递归调用获取连接，ps：不建议这么干如果没有连接
		// 2.线程通讯，添加入后通知获取
		else {
			try {
				connectionsPool.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getConnection();
		}

	}

	public void free(Connection conn) {
		synchronized (connectionsPool) {
			this.connectionsPool.addLast(conn);
			connectionsPool.notify();
		}
	}

}
