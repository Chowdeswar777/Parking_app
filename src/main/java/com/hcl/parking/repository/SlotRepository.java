package com.hcl.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parking.entity.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

	Optional<Slot> findByRegistrationId(Integer registrationId);

}
