package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

public class BaseAction implements SessionAware {

	protected Map<String, Object> session;

	public void setSession(Map<String, Object> arg0) {
		session = arg0;

	}

}
