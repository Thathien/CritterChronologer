package com.udacity.jdnd.course3.critter.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDTO {
	private long id;
	private String name;
	private String phoneNumber;
	private String notes;
	private List<Long> petIds;
}
