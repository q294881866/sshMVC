package struts;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;
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

	/** 创建一个线程池：模拟servlet的多实例运行 */
	static final ExecutorService cachedThreadPool = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);

	protected static String classpath;
	protected static StrutsManager manager;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		cachedThreadPool.execute(new ActionHandler(req,res,manager));
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init struts config");
		classpath = StrutsFilter.class.getResource("/").getPath();

		System.out.println("classPath =" + classpath);
		try {
			manager = StrutsManager.readStrutsXml(new File(classpath,
					"struts.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
