package com.udacity.jdnd.course3.critter.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

public class ConvertDTO {

	public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		return employeeDTO;
	}

	public static Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		return employee;
	}

	public static CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);

		List<Long> petIds = new ArrayList<>();
		List<Pet> pets = customer.getPets();
		if (pets != null) {
			for (Pet pet : pets) {
				petIds.add(pet.getId());
			}
		}

		customerDTO.setPetIds(petIds);
		return customerDTO;
	}

	public static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(schedule, scheduleDTO);

		List<Long> petIds = new ArrayList<>();
		if (schedule.getPets() != null) {
			schedule.getPets().forEach(pet -> petIds.add(pet.getId()));
		}

		List<Long> employeeIds = new ArrayList<>();
		if (schedule.getEmployees() != null) {
			schedule.getEmployees().forEach(employee -> employeeIds.add(employee.getId()));
		}

		scheduleDTO.setPetIds(petIds);
		scheduleDTO.setEmployeeIds(employeeIds);
		return scheduleDTO;
	}
	

	public static PetDTO convertPetToPetDTO(Pet pet) {
		PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(pet, petDTO);
		petDTO.setOwnerId(pet.getCustomer().getId());
		return petDTO;
	}

}
