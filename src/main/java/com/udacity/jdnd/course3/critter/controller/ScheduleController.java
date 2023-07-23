package com.udacity.jdnd.course3.critter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udacity.jdnd.course3.critter.services.CustomerServives;
import com.udacity.jdnd.course3.critter.services.EmployeeServives;
import com.udacity.jdnd.course3.critter.services.PetServives;
import com.udacity.jdnd.course3.critter.services.SheduleServives;
import com.udacity.jdnd.course3.critter.utils.ConvertDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private EmployeeServives employeeService;

	@Autowired
	private PetServives petService;

	@Autowired
	private SheduleServives scheduleService;

	@Autowired
	private CustomerServives customerService;

	@PostMapping
	@ResponseBody
	public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);
		schedule = scheduleService.save(schedule);
		return ConvertDTO.convertScheduleToScheduleDTO(schedule);

	}

	@GetMapping
	@ResponseBody
	public List<ScheduleDTO> getAllSchedules() {
		List<Schedule> schedules = scheduleService.getAllShedule();
		return convertListScheduleToListScheduleDTO(schedules);
	}

	@GetMapping("/pet/{petId}")
	@ResponseBody
	public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
		List<Schedule> schedules = scheduleService.getSchedulebyPetId(petId);
		return convertListScheduleToListScheduleDTO(schedules);
	}

	@GetMapping("/employee/{employeeId}")
	@ResponseBody
	public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
		List<Schedule> schedules = scheduleService.getSchedulebyEmployeeId(employeeId);
		return convertListScheduleToListScheduleDTO(schedules);
	}

	@GetMapping("/customer/{customerId}")
	@ResponseBody
	public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
		Customer customer = customerService.findCustomerById(customerId);
		List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
		if (customer.getPets() != null) {
			for (Pet pet : customer.getPets()) {
				List<Schedule> schedulesByPetsId = scheduleService.getSchedulebyPetId(pet.getId());
				scheduleDTOs.addAll(convertListScheduleToListScheduleDTO(schedulesByPetsId));
			}
		}
		return scheduleDTOs;
	}

	public List<ScheduleDTO> convertListScheduleToListScheduleDTO(List<Schedule> schedules) {
		List<ScheduleDTO> ScheduleDTOs = new ArrayList<>();
		schedules.forEach(schedule -> ScheduleDTOs.add(ConvertDTO.convertScheduleToScheduleDTO(schedule)));
		return ScheduleDTOs;
	}

	public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);

		List<Pet> pets = new ArrayList<>();
		if (scheduleDTO.getPetIds() != null) {
			scheduleDTO.getPetIds().forEach(petId -> pets.add(petService.getPetByID(petId)));
		}

		List<Employee> employees = new ArrayList<>();
		if (scheduleDTO.getEmployeeIds() != null) {
			scheduleDTO.getEmployeeIds()
					.forEach(employeeId -> employees.add(employeeService.getEmployeeById(employeeId)));
		}
		schedule.setPets(pets);
		schedule.setEmployees(employees);
		return schedule;
	}
}
