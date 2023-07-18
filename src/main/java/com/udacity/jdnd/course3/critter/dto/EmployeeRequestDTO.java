package com.udacity.jdnd.course3.critter.dto;

import java.time.LocalDate;
import java.util.Set;

import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequestDTO {

	private Set<EmployeeSkill> skills;
	private LocalDate date;
}
