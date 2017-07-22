package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DBUtils;
import dao.IUser;
import entity.User;

public class UserDAOImpl implements IUser {

	Connection conn = null;
	PreparedStatement pre = null;
	ResultSet rst = null;
	User user = null;

	public User findByUserName(String name) throws Exception {

		try {
			conn = DBUtils.getConnection();
			pre = conn
					.prepareStatement("select * from t_uemp where username=?");
			pre.setString(1, name);
			rst = pre.executeQuery();
			while (rst.next()) {
				int id = rst.getInt("id");
				String username = rst.getString("username");
				String password = rst.getString("password");
				String gender = rst.getString("gender");
				user = new User(id, username, password, gender);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public static void main(String[] args) {
		try {
			UserDAOImpl u = new UserDAOImpl();
			User user = u.findByUserName("tang");
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
