package struts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StrutsFilter implements Filter {

	/** 创建一个线程池,系统可用线程的3倍 */
	static final ExecutorService cachedThreadPool = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);

	protected  String classpath;//工厂根路径
	protected  StrutsManager manager;//处理相关请求的Struts配置文件管理类

	/**
	 * 应用服务被停止或重新装载了，则会执行Filter的destroy方法，
	 * Filter对象销毁。 只调用一次
	 */
	public void destroy() {
	}

	/**
	 * filter对象初始化好后，多次调用
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		cachedThreadPool.execute(new ActionHandler(req, res, manager));
	}

	/**
	 * Filter对象实例后，调用init()方法，只有一次
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init struts config");
		classpath = StrutsFilter.class.getResource("/").getPath();
		try {
			manager = StrutsManager.readStrutsXml(new File(classpath,
					"struts.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
