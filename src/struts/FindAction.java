package struts;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAction {

	// static Map<String, List<ActionEntity>> map = new HashMap<String,
	// List<ActionEntity>>();

	@SuppressWarnings("unchecked")
	public static String findResult(StringBuffer url,
			List<PackageEntity> packageEntityList, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// 3.获取请求的action，如***/weixin/user_list，获取weixin和user_list
		String[] urltemp = url.toString().split("/");
		int last = urltemp.length - 1;
		String Id = urltemp[last - 1];// weixin
		String action = urltemp[last];// user_list
		// String actionUrl = urltemp[last - 1]+"."+urltemp[last];

		// 4.包id与weixin相同返回actionEntities
		List<ActionEntity> actionEntities;
		for (PackageEntity packageEntity : packageEntityList) {
			String packageId = packageEntity.getId();
			if (packageId.equals(Id)) {
				actionEntities = packageEntity.getActionEntities();
				String actionNameTemp;
				for (ActionEntity actionEntity : actionEntities) {
					// 5.如user_*变成user_
					actionNameTemp = actionEntity.getActionName().replace("*",
							"");
					if (action.contains(actionNameTemp)) {
						// 6.如user_list变成list
						System.err.println("actionNameTemp==" + actionNameTemp);
						String methodName = action.replace(actionNameTemp, "");
						// 7.如加载action的list方法
						Class<?> atctionClass = actionEntity.getActionClass();

						ActionSupport actionSupport = (ActionSupport) atctionClass
								.newInstance();
						// 传入request、response
						actionSupport.setRequestAndResponse(req, res);

						Method method2 = atctionClass.getMethod(methodName,
								null);
						if (String.class.isAssignableFrom(method2
								.getReturnType())) {// 如果返回值类型是String执行返回结果

							String resultValue = (String) method2.invoke(
									actionSupport, null);
							System.out.println("resultValue==" + resultValue);
							/**
							 * 8.处理返回的结果如果请求返回结果与 name相等 返回结果 如<result
							 * name="list2"
							 * >/WEB-INF/jsp/userAction/list.jsp</result>
							 */
							List<ResultEntity> resultEntities = actionEntity
									.getResultEntities();
							for (ResultEntity resultEntity : resultEntities) {
								if (resultEntity.getResultName().equals(
										resultValue)) {
									System.out
											.println("resultEntity.getResultValue()"
													+ resultEntity
															.getResultValue());
									return resultEntity.getResultValue();
								}
							}
						} else if ("void".equals(method2.getReturnType()
								.toString())) {
							method2.invoke(actionSupport, null);
							return "void";
						}
					}
				}

			}
		}
		return null;
	}

	// public static void main(String[] args) {
	// System.out.println("user_".replace("*", ""));
	// }
}

/**
 * 定义线程的返回值
 */
class returnCB implements Callable<String> {

	private String result;

	public returnCB(String result) {
		this.result = result;
	}

	@Override
	public String call() throws Exception {
		return result;
	}

}