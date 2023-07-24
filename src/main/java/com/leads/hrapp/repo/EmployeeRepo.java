package com.leads.hrapp.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leads.hrapp.model.Employee;

@Repository
public interface  EmployeeRepo extends CrudRepository<Employee, Integer>{
	
	@Query("SELECT e FROM Employee e WHERE e.id = :employeeId")
	public Employee findByEmployeeId(@Param("employeeId") int employeeId);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO employee(\n" + 
			"	id, building, division, firstname, lastname, room, title)\n" + 
			"	VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	public void upload(@Param("employeeId") int employeeId, @Param("title") String title, @Param("firstname") String firstname, 
			@Param("lastname") String lastname, @Param("division") String division, @Param("building") String building, @Param("room") String room);

	
}
