package com.udacity.jdnd.course3.critter.entity;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Employee extends User {

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<EmployeeSkill> skills = new HashSet<>();

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<DayOfWeek> dayAvailable = new HashSet<>();

}
