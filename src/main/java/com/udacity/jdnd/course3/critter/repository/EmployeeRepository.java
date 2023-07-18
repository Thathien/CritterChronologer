package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT * FROM emplyee a INNER JOIN emplyee_day_available b ON a.id=b.employee_id WHERE day_available = :date", nativeQuery = true)
	List<Employee> findByDaysAvailable(DayOfWeek date);
}
