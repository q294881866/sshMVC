package struts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionHandler implements Runnable {
	private ActionSupport actionSupport;//请求处理类
	private ActionEntity a;//配置文件中的action节点
	private String id, action, methodName;//包，类名，方法名
	private HttpServletResponse res;//请求
	private HttpServletRequest req;//应答
	private StrutsManager manager;//配置文件管理类
	private Method m ;//请求方法

	public ActionHandler(HttpServletRequest req, HttpServletResponse res,
			StrutsManager manager) {
		this.req = req;
		this.res = res;
		this.manager = manager;
	}
	
	@Override
	public void run() {
		// 1.处理返回消息
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		// 2.不论GET或POST都可以通过getRequestURL+getParameterMap()来得到请求完整路径
		StringBuffer url = req.getRequestURL();

		try {
			invoke(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析请求的url，找到对应的处理逻辑并返回值
	 * @param url
	 * @param req
	 *            请求，带参数
	 * @param res
	 *            返回，带参数
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws InvocationTargetException 
	 * @throws Exception
	 */
	public void invoke(StringBuffer url) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ServletException, IOException  {
		parseUrl(url);
		getMethod();
		doResponse(); 
	}

	public void doResponse() throws IllegalAccessException,
			InvocationTargetException, ServletException, IOException {
		if (String.class.isAssignableFrom(m.getReturnType())) {
			String res1 = (String) m.invoke(actionSupport, null);
			String resJsp = a.getResult(res1).getValue();
			if (resJsp.endsWith("jsp")) {
				req.getRequestDispatcher(resJsp).forward(req, res);
			}else {
				res.sendRedirect(req.getContextPath() + resJsp);
			}
		} else if ("void".equals(m.getReturnType().toString())) {
			m.invoke(actionSupport, null);
			System.err.println("void method filter");//这里要写日志
		}
	}

	public void getMethod() throws InstantiationException,
			IllegalAccessException, NoSuchMethodException {
		a = manager.getPackage(id).getAction(action);
		Class<ActionSupport> atctionClass = a.getActionClass();

		actionSupport = atctionClass.newInstance();
		// 传入request、response
		actionSupport.setRequestAndResponse(req, res);

		m = atctionClass.getMethod(methodName, null);
	}

	/**
	 * 请求路径的解析<br>
	 * 如:host/application/weixin/user_test_list<br>
	 * 1.id=weixin<br>
	 * 2.action=user_test_*<br>
	 * 3.methodName=list<br>
	 * @param url
	 */
	public void parseUrl(StringBuffer url) {
		String[] urltemp = url.toString().split("/");
		int last = urltemp.length - 1;
		id = urltemp[last - 1];// weixin
		String[] tmp = urltemp[last].split("_");// user_test_list
		methodName = tmp[tmp.length - 1];// list
		action = urltemp[last].replace(methodName, "") + "*";// user_test_*
	}

	
}
