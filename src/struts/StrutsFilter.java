package struts;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	static final ExecutorService cachedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*3);

	protected static String classpath;
	protected static StrutsManager manager;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		cachedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				// 1.处理接收消息
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/html;charset=UTF-8");
				// 2.不论GET或POST都可以通过getRequestURL+getParameterMap()来得到请求完整路径
				StringBuffer url = req.getRequestURL();

				// 4.从map中找到处理这个请求的类
				String resultJsp = null;
				try {
					resultJsp = manager.invoke(url, req, res);
					if (null == resultJsp) {
						System.err.println("err null");
					} else if (resultJsp.endsWith(".jsp")) {
						req.getRequestDispatcher(resultJsp).forward(req, res);
					} else if ("void".equals(resultJsp)) {
						System.err.println("void method filter");
					} else {
						res.sendRedirect(req.getContextPath() + resultJsp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init struts config");
		classpath = StrutsFilter.class.getResource("/").getPath();

		System.out.println("classPath =" + classpath);
		try {
			manager = StrutsManager.readStrutsXml(
					new File(classpath, "struts.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
