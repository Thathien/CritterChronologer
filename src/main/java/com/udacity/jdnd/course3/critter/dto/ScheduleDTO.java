package com.udacity.jdnd.course3.critter.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
	private long id;
	private List<Long> employeeIds;
	private List<Long> petIds;
	private LocalDate date;
	private Set<EmployeeSkill> activities;
}
