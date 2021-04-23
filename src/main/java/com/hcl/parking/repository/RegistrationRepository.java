package com.hcl.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parking.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	Optional<Registration> findByMobileNumber(String mobileNumber);

}
