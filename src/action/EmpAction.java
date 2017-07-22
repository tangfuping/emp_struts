package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.IEmployeeDAO;
import entity.Employee;
import utils.Factory;

public class EmpAction extends ActionSupport implements ModelDriven<Employee> {
	// 初始化实体类对象
	private Employee employee = new Employee();
	HttpServletRequest request = ServletActionContext.getRequest();
	IEmployeeDAO dao = (IEmployeeDAO) Factory.getInstance("IEmployeeDAO");

	public Employee getModel() {
		return employee;
	}

	public String execute() {

		try {
			List<Employee> mlist = dao.findAll("asc",1);
			// System.out.println(mlist);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("employees", mlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String list() {

		try {
			String orderby = "asc";
			int page=1;
			if(request.getParameter("curPage")!=null){
				page=Integer.valueOf(request.getParameter("curPage"));
			}
			List<Employee> mlist = dao.findAll(orderby,page);
			// System.out.println(mlist);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("employees", mlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String orderBy() {
		try {
			int page=1;
			if(request.getParameter("curPage")!=null){
				page=Integer.valueOf(request.getParameter("curPage"));
			}
			String orderby = request.getParameter("orderby");
			System.out.println(orderby);
			List<Employee> mlist = dao.findAll(orderby,page);
			// System.out.println(mlist);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("employees", mlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String toAddPage() {
		System.out.println("add.....");
		return "toAddPage";
	}

	public String add() {

		try {
			// System.out.println(employee);
			dao.add(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加失败！");
		}
		return "add";
	}

	public String del() {

		try {
			int id = Integer.valueOf(request.getParameter("id"));
			// System.out.println(id);
			dao.deleleById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("删除失败！");
		}
		return "del";
	}

	public String update() {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			System.out.println(id);
			System.out.println("正在添加、、、");
			Employee ee = dao.findById(id);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("employee", ee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加失败！");
		}
		return "update";
	}

	public String updateEmp() {
		try {
			System.out.println("要修改的信息： " + employee);
			dao.update(employee);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加失败！");
		}
		return "updateEmp";
	}

	public String findByName() {

		try {
			String sqlname = request.getParameter("sqlname");
			System.out.println(sqlname);
			List<Employee> mlist1 = dao.findByName(sqlname);
			System.out.println(mlist1);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("employeeByName", mlist1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finByName";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
