package com.udacity.jdnd.course3.critter.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private EmployeeServives employeeService;

	@Autowired
	private CustomerServives customerService;

	@Autowired
	private PetServives petService;

	@PostMapping("/customer")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = convertCustomerDTOToCustomer(customerDTO);
		customer = customerService.save(customer);
		return ConvertDTO.convertCustomerToCustomerDTO(customer);
	}

	@GetMapping("/customer")
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerService.findAllCustomers();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			customerDTOs.add(ConvertDTO.convertCustomerToCustomerDTO(customer));
		}
		return customerDTOs;
	}

	@GetMapping("/customer/pet/{petId}")
	public CustomerDTO getOwnerByPet(@PathVariable long petId) {
		Pet pet = petService.getPetByID(petId);
		return ConvertDTO.convertCustomerToCustomerDTO(pet.getCustomer());
	}

	@PostMapping("/employee")
	public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = ConvertDTO.convertEmployeeDTOToEmployee(employeeDTO);
		employee = employeeService.save(employee);
		return ConvertDTO.convertEmployeeToEmployeeDTO(employee);
	}

	@PostMapping("/employee/{employeeId}")
	public EmployeeDTO getEmployee(@PathVariable long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ConvertDTO.convertEmployeeToEmployeeDTO(employee);
	}

	@PutMapping("/employee/{employeeId}")
	//@RequestMapping(value = "/employee/{employeeId}", produces = "application/json",method = RequestMethod.PUT)
	public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
		employeeService.setDayAvaiable(daysAvailable, employeeId);
	}

	@GetMapping("/employee/availability")
	public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		Set<EmployeeSkill> employeeSkills = employeeRequestDTO.getSkills();
		DayOfWeek daysAvailable = employeeRequestDTO.getDate().getDayOfWeek();
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
