package com.leads.hrapp.service;

import com.leads.hrapp.model.Employee;


public interface EmployeeService {
	
	public Employee save(Employee employee);
	
	public void upload(Employee employee);
	
	public Iterable<Employee> findAll();
	
	public Employee findById (int employeeId);
	
	public Employee findByName (String employeeName);
	
	public void deleteById(int employeeId);
}
