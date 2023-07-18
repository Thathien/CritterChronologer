package com.udacity.jdnd.course3.critter.dto;

import java.time.DayOfWeek;
import java.util.Set;

import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;
}
