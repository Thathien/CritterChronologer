package com.udacity.jdnd.course3.critter.services;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.EmployeeException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class EmployeeServives {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeException("Employee is not found with ID: " + id));
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> findByDayAvaiable(DayOfWeek dayOfWeek) {
		return employeeRepository.findByDaysAvailable(dayOfWeek);
	}

	public void setDayAvaiable(Set<DayOfWeek> dayOfWeeksAvaiable, Long id) {
		Employee employee = getEmployeeById(id);
		employee.setDaysAvailable(dayOfWeeksAvaiable);
		employeeRepository.save(employee);
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		Schedule schedule = new Schedule();
		schedule.setEmployees(employees);
		scheduleRepository.save(schedule);
	}

}
