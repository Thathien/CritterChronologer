package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.ScheduleException;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class SheduleServives {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public List<Schedule> getAllShedule() {
		return scheduleRepository.findAll();
	}

	public Schedule findScheduleById(Long id) {
		return scheduleRepository.findById(id)
				.orElseThrow(() -> new ScheduleException("Schedule is not found with ID: " + id));
	}

	public Schedule save(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	public List<Schedule> getSchedulebyEmployeeId(Long id) {
		return scheduleRepository.getSchedulebyEmployeeId(id);
	}

	public List<Schedule> getSchedulebyPetId(Long id) {
		return scheduleRepository.getSchedulebyPetId(id);
	}

}
