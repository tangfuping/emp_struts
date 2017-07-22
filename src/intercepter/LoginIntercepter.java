package intercepter;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * login拦截器，拦截登陆功能
 * 在user 是否等于null的情况
 * 
 *
 */
public class LoginIntercepter extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//1.从session中取出用户名
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		//2.判断obj是否为null
		if(obj !=null){
			//不登录状态，执行action方法，放行
			return invocation.invoke();
		}else{
			//登陆状态 
			//写方法名称即可，返回登陆界面
			return "login";
		}
		
	}

	

}
