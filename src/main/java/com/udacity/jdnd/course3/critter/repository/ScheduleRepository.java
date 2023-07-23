package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query(value = "SELECT * FROM schedule a INNER JOIN schedule_employees b on a.id=b.schedule_id WHERE a.id = :idEmployee", nativeQuery = true)
	List<Schedule> getSchedulebyEmployeeId(@Param("idEmployee") Long idEmployee);

	@Query(value = "SELECT * FROM schedule a INNER JOIN schedule_pets b on a.id=b.schedule_id WHERE a.id = :idPet", nativeQuery = true)
	List<Schedule> getSchedulebyPetId(@Param("idPet") Long idPet);
}
