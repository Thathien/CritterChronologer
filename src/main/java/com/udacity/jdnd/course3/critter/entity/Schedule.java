package com.udacity.jdnd.course3.critter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToMany
	private List<Employee> employees = new ArrayList<>();

	@ManyToMany
	private List<Pet> pets = new ArrayList<>();

	private LocalDate date;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<EmployeeSkill> activities;
}
