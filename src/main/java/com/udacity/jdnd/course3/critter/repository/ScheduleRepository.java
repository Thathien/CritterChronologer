package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query(value = "SELECT * FROM ", nativeQuery=true)
	List<Schedule> getSchedulebyEmployeeId(Long id);

	@Query(value = "SELECT * FROM ", nativeQuery=true)
	List<Schedule> getSchedulebyPetId(Long id);
}
