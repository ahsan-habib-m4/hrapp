package com.leads.hrapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leads.hrapp.model.Employee;
import com.leads.hrapp.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired EmployeeRepo emrepo;

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return emrepo.save(employee);
	}
	
	@Override
	@Transactional
	public void upload(Employee employee) {
		emrepo.upload(employee.getId(), 
				employee.getTitle(), 
				employee.getFirstname(), 
				employee.getLastname(),
				employee.getDivision(),
				employee.getBuilding(),
				employee.getRoom());
	}

	@Override
	public Iterable<Employee> findAll() {
		return emrepo.findAll();
	}

	@Override
	public Employee findById(int employeeId) {
		return emrepo.findByEmployeeId(employeeId);
	}

	@Override
	public Employee findByName(String employeeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int employeeId) {
		emrepo.deleteById(employeeId);
	}

}
