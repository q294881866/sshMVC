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

import jdbcUtils.core.IdCache4Java;

public class StrutsFilter implements Filter {

	/** 创建一个线程池：模拟servlet的多实例运行 */
	static final ExecutorService cachedThreadPool = Executors
			.newCachedThreadPool();

	String classpath;
	static List<PackageEntity> packageEntityList;

	public void destroy() {
		// TODO Auto-generated method stub

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

				System.out.println("获得struts请求: 方式" + url);
				// 4.从map中找到处理这个请求的类
				String resultJsp = null;
				try {
					resultJsp = FindAction.findResult(url, packageEntityList,
							req, res);
					System.out.println(resultJsp + "resJsp");
					if (null == resultJsp) {
						System.err.println("err null");
						// FIXME 过滤器首页设置，这里什么都没做
					} else if (resultJsp.endsWith(".jsp")) {
						req.getRequestDispatcher(resultJsp).forward(req,
								res);
					} else if ("void".equals(resultJsp)) {
						// FIXME void method do nothing
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
		String classPath = StrutsFilter.class.getResource("/").getPath();

		System.out.println("classPath =" + classPath);
		try {
			packageEntityList = new LoadStrutsXml().readStrutsXml(new File(
					classPath, "struts.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// IdCache4Java.java初始化操作
		try {
			IdCache4Java.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { try { new
	 * StrutsFilter().init(null); } catch (ServletException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } StringBuffer buffer=
	 * new StringBuffer().append(// "/hthost/336/weixin/user_list"); try {
	 * FindAction.findResult(buffer , packageEntityList, null, null); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * }
	 */

}
