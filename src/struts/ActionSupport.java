package struts;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class ActionSupport {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public void setRequestAndResponse(HttpServletRequest req,HttpServletResponse res) {
//		System.out.println("HttpServletRequest=="+req+" HttpServletResponse= "+res);
		this.request = req;
		this.response = res;
		
	}
	
	/**
	 * 获取request流对象
	 * @return 
	 * 		返回string 字符串
	 */
	protected String getStringBuffer() {
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    (ServletInputStream) request.getInputStream(), "utf-8"));  
            StringBuffer sb = new StringBuffer("");  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                sb.append(temp);  
            }  
            br.close();  
           return sb.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return null;  
	} 
	
	
}
