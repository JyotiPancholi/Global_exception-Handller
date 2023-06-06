package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.custom.exception.EmptyInputException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface{
	
	@Autowired
	private EmployeeCrudRepo crudRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		if (employee.getName().isEmpty() || employee.getName().length()==0) {
			throw new EmptyInputException("601","Input Field is Empty !");
		} else {
			Employee savedEmployee = crudRepo.save(employee);
			return savedEmployee;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = crudRepo.findAll();
		if (list.isEmpty()) {
			throw new BusinessException("604","List is Empty, Please Insert values first");
		}
		return list;
	}

	@Override
	public Employee getEmpById(Long empidL) {
		return crudRepo.findById(empidL).get();
	}

	@Override
	public void deleteEmpById(Long empidL) {
		crudRepo.deleteById(empidL);
	}

}
