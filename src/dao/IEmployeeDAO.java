package dao;

import java.util.List;

import entity.Employee;

public interface IEmployeeDAO {
	public List<Employee> findAll(String orderby,int page) throws Exception;

	public void add(Employee employee) throws Exception;

	// 删除的方法
	public void deleleById(int id) throws Exception;

	// 通过id查对应的信息
	public Employee findById(int id) throws Exception;

	// 更新数据
	public void update(Employee e) throws Exception;

	public List<Employee> findByName(String sqlname) throws Exception;
}
