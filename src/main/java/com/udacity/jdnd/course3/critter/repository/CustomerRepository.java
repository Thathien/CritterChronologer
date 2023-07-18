package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query(value = "SELECT * FROM ", nativeQuery=true)
	List<Employee> findDayAvaiable(DayOfWeek date);
}
