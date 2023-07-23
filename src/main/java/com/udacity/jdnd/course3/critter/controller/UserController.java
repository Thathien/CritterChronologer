package com.udacity.jdnd.course3.critter.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerServives;
import com.udacity.jdnd.course3.critter.services.EmployeeServives;
import com.udacity.jdnd.course3.critter.services.PetServives;
import com.udacity.jdnd.course3.critter.utils.ConvertDTO;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private EmployeeServives employeeService;

	@Autowired
	private CustomerServives customerService;

	@Autowired
	private PetServives petService;

	@PostMapping("/customer")
	@ResponseBody
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = convertCustomerDTOToCustomer(customerDTO);
		customer = customerService.save(customer);
		return ConvertDTO.convertCustomerToCustomerDTO(customer);
	}

	@GetMapping("/customer")
	@ResponseBody
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerService.findAllCustomers();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			customerDTOs.add(ConvertDTO.convertCustomerToCustomerDTO(customer));
		}
		return customerDTOs;
	}

	@GetMapping("/customer/pet/{petId}")
	@ResponseBody
	public CustomerDTO getOwnerByPet(@PathVariable long petId) {
		Pet pet = petService.getPetByID(petId);
		return ConvertDTO.convertCustomerToCustomerDTO(pet.getCustomer());
	}

	@PostMapping("/employee")
	@ResponseBody
	public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = ConvertDTO.convertEmployeeDTOToEmployee(employeeDTO);
		employee = employeeService.save(employee);
		return ConvertDTO.convertEmployeeToEmployeeDTO(employee);
	}

	@PostMapping("/employee/{employeeId}")
	@ResponseBody
	public EmployeeDTO getEmployee(@PathVariable long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ConvertDTO.convertEmployeeToEmployeeDTO(employee);
	}

	@PutMapping("/employee/{employeeId}")
	@ResponseBody
	public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
		employeeService.setDayAvaiable(daysAvailable, employeeId);
	}

	@ResponseBody
	@GetMapping("/employee/availability")
	public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		Set<EmployeeSkill> employeeSkills = employeeRequestDTO.getSkills();
		DayOfWeek daysAvailable = employeeRequestDTO.getDate().getDayOfWeek();
		System.out.println(daysAvailable);
		List<Employee> employees = employeeService.findByDayAvaiable(daysAvailable);
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getSkills().containsAll(employeeSkills)) {
				employeeDTOs.add(ConvertDTO.convertEmployeeToEmployeeDTO(employee));
			}
		}
		return employeeDTOs;
	}

	public Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);

		List<Pet> pets = new ArrayList<>();
		List<Long> petIds = customerDTO.getPetIds();
		if (petIds != null) {
			for (Long petId : petIds) {
				pets.add(petService.getPetByID(petId));
			}
		}

		customer.setPets(pets);
		return customer;
	}

}
