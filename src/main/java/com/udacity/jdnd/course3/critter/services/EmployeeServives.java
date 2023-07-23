package com.udacity.jdnd.course3.critter.services;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.exception.EmployeeException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServives {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeException("Employee is not found with ID: " + id));
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> findByDayAvaiable(DayOfWeek dayOfWeek) {
		return employeeRepository.findByDayAvailable(dayOfWeek);
	}

	public void setDayAvaiable(Set<DayOfWeek> dayOfWeeksAvaiable, Long id) {
		Employee employee = getEmployeeById(id);
		employee.setDayAvailable(dayOfWeeksAvaiable);
		employeeRepository.save(employee);
	}

}
