package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtils;

import dao.IEmployeeDAO;
import entity.Employee;

public class EmployeeDAOimpl implements IEmployeeDAO {

	public List<Employee> findAll(String orderby,int page) throws Exception {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rst = null;
		List<Employee> mList = new ArrayList<Employee>();
		try {
			conn = DBUtils.getConnection();
			prep = conn
					.prepareStatement("select * from t_emp order by empsalary "
							+ orderby + " limit "+page+",5");
			rst = prep.executeQuery();
			while (rst.next()) {
				int id = rst.getInt("t_id");
				String name = rst.getString("empname");
				Double salary = rst.getDouble("empsalary");
				int age = rst.getInt("empage");
				Employee e = new Employee(id, name, age, salary);
				mList.add(e);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rst != null) {
				try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (prep != null) {
				try {
					prep.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return mList;
	}

	public static void main(String[] args) {
		EmployeeDAOimpl e = new EmployeeDAOimpl();
		try {
			List<Employee> mlist = e.findAll("asc",1);
			for (Employee employee : mlist) {
				System.out.println(employee);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void add(Employee employee) throws Exception {
		Connection conn = DBUtils.getConnection();
		PreparedStatement prep = conn
				.prepareStatement("insert into t_emp(empname,empsalary,empage) values(?,?,?)");
		prep.setString(1, employee.getEmpname());
		prep.setDouble(2, employee.getEmpsalary());
		prep.setInt(3, employee.getEmpage());

		prep.executeUpdate();
		if (prep != null) {
			prep.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

	public void deleleById(int id) throws Exception {
		Connection conn = DBUtils.getConnection();
		PreparedStatement prep = conn
				.prepareStatement("delete from t_emp where t_id=?");
		prep.setInt(1, id);
		prep.executeUpdate();
		if (prep != null) {
			prep.close();
		}
		if (conn != null) {
			conn.close();
		}

	}

	public Employee findById(int id) throws Exception {
		Employee e = null;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rst = null;
		try {
			conn = DBUtils.getConnection();
			prep = conn.prepareStatement("select * from t_emp where t_id=?");
			prep.setInt(1, id);
			rst = prep.executeQuery();
			if (rst.next()) {
				e = new Employee();
				e.setT_id(id);
				e.setEmpname(rst.getString("empname"));
				e.setEmpsalary(rst.getDouble("empsalary"));
				e.setEmpage(rst.getInt("empage"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (rst != null) {
				rst.close();
			}
			if (prep != null) {
				prep.close();
			}
			DBUtils.close(conn);
		}

		return e;
	}

	public List<Employee> findByName(String sqlname) throws Exception {

		Connection conn = null;
		ResultSet rst = null;
		PreparedStatement prep = null;
		List<Employee> mList = new ArrayList<Employee>();
		try {
			String sql = "select * from t_emp where empname like ?";

			conn = DBUtils.getConnection();
			prep = conn.prepareStatement(sql);
			prep.setString(1, "%" + sqlname + "%");
			rst = prep.executeQuery();
			while (rst.next()) {
				int id = rst.getInt("t_id");
				String name = rst.getString("empname");
				Double salary = rst.getDouble("empsalary");
				int age = rst.getInt("empage");
				Employee e = new Employee(id, name, age, salary);
				mList.add(e);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (rst != null) {
				rst.close();
			}
			if (prep != null) {
				prep.close();
			}
			DBUtils.close(conn);
		}

		return mList;
	}

	public void update(Employee e) throws Exception {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBUtils.getConnection();
			prep = conn.prepareStatement("update t_emp "
					+ "set empname=?,empsalary=?,empage=? where t_id=?");
			prep.setString(1, e.getEmpname());
			prep.setDouble(2, e.getEmpsalary());
			prep.setInt(3, e.getEmpage());
			prep.setInt(4, e.getT_id());
			prep.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			if (prep != null) {
				prep.close();
			}
			DBUtils.close(conn);
		}

	}

}
