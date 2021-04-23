package com.hcl.parking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.LoginDto;
import com.hcl.parking.dto.LoginResponseDto;
import com.hcl.parking.entity.Registration;
import com.hcl.parking.entity.Role;
import com.hcl.parking.exception.UserNotFoundException;
import com.hcl.parking.repository.RoleRepository;
import com.hcl.parking.repository.UserRepository;
import com.hcl.parking.util.PasswordUtil;

/**
 * 
 * @author Venkat
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	/**
	 * This method is intended to login the user with proper credentials
	 *
	 * @param LoginDto is the input object which mobileNo, password
	 * @exception USER_NOT_FOUND
	 * @return ReleaseResponseDto which returns registrationId,message, roleName
	 */
	@Override
	public LoginResponseDto loginUser(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		PasswordUtil passwordUtil = new PasswordUtil();
		logger.info("inside the loginUser method..");
		Optional<Registration> registration = userRepository.findByMobileNumber(loginDto.getMobileNo());
		if (!registration.isPresent())
			throw new UserNotFoundException("Invalid credentials");
		Optional<Role> role = roleRepository.findById(registration.get().getRoleId());
		if (!role.isPresent())
			throw new UserNotFoundException("Role id not available");
		if (registration.get().getMobileNumber().equalsIgnoreCase(loginDto.getMobileNo())
				&& registration.get().getPassword().equals(passwordUtil.encodePassword(loginDto.getPassword()))) {
			loginResponseDto.setMessage("Login success");
			loginResponseDto.setRegId(registration.get().getRegistrationId());
			loginResponseDto.setRoleName(role.get().getRoleName());
		}
		return loginResponseDto;
	}
}
