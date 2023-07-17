package com.udacity.jdnd.course3.critter.dto.request;

import java.time.LocalDate;

import com.udacity.jdnd.course3.critter.entity.PetType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetDTO {
	private long id;
	private PetType type;
	private String name;
	private long ownerId;
	private LocalDate birthDate;
	private String notes;
}
