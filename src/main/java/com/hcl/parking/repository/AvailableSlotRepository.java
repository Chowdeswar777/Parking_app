package com.hcl.parking.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcl.parking.entity.AvailableSlot;

public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, Integer> {

	List<AvailableSlot> findByAvailableDate(LocalDate availableDate);

//	@Query(value="SELECT * FROM parkingdb.available_slot  WHERE available_slot_id =?1 FOR UPDATE",nativeQuery = true)
//	AvailableSlot lockSlot(int availableSlotId);

	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("SELECT s FROM AvailableSlot s WHERE s.availableSlotId= :availableSlotId and s.status='Available'")
	AvailableSlot lockSlot(@Param("availableSlotId") int availableSlotId);
	

}

