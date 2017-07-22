package action;

import javax.servlet.http.HttpSession;

import dao.IUser;
import entity.User;
import utils.Factory;

public class UserAction extends BaseAction{
	private String username;
	private String password;
	private String number;
	private String errorMsg;

	public String login() {
		//取出session中的验证码
		String imageCode=(String) session.get("imageCode");
		//System.out.println("系统加载的验证码"+imageCode);
		//System.out.println("用户输入的验证码"+number);
		//System.out.println(username);
		if(!number.equalsIgnoreCase(imageCode)){
			errorMsg="验证码不正确";
			return "fail";
		}
		IUser dao=(IUser) Factory.getInstance("IUser");
		User user=null;
		try {
			user=dao.findByUserName(username);
			if (user==null) {
				errorMsg="用户名不存在";
				return "fail";
			}else if(!user.getPassword().equals(password)){
				errorMsg="密码错误";
				return "fail";
			}else{
				session.put("user", user);
				//HttpSession session = request.getSession();
				//session.setAttribute("username", username);//存入用户名
				return "ok";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "null";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
