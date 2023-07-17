package com.udacity.jdnd.course3.critter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {

	private String phoneNumber;

	private String notes;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Pet> pets = new ArrayList<>();
}
