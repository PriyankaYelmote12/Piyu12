package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;



@Repository
public interface xlsrepo extends JpaRepository<Employee, Integer>{
	
	Employee getEmployeeByEid(int eid);

}
