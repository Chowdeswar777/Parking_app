package com.hcl.parking.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.parking.dto.LoginDto;
import com.hcl.parking.dto.LoginResponseDto;
import com.hcl.parking.entity.Registration;
import com.hcl.parking.entity.Role;
import com.hcl.parking.repository.RoleRepository;
import com.hcl.parking.repository.UserRepository;
import com.hcl.parking.util.PasswordUtil;

/**
 * 
 * @author Venkat
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	RoleRepository roleRepository;
	@InjectMocks
	LoginServiceImpl loginService;

	Registration registration = getRegistration();
	LoginDto loginDto = getLoginDto();
	LoginResponseDto loginResponseDto = getLoginResponseDto();
	Role role = getRoleDetails();
	PasswordUtil pass = new PasswordUtil();

	public Registration getRegistration() {
		Registration registration = new Registration();
		registration.setMobileNumber("97392313");
		registration.setOverAllExperience(34.5F);
		registration.setHclExperience(5F);
		registration.setPassword("password");
		registration.setRegistrationId(1);
		registration.setRoleId(2);
		registration.setUserName("guru");
		return registration;
	}

	public LoginResponseDto getLoginResponseDto() {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setRegId(1);
		loginResponseDto.setRoleName("V");
		loginResponseDto.setMessage("Login success");
		return loginResponseDto;
	}

	public LoginDto getLoginDto() {
		LoginDto loginDto = new LoginDto();
		loginDto.setMobileNo("97392313");
		
		String password = "1234";
	pass.encodePassword(password);
		loginDto.setPassword(pass.encodePassword(password));
		return loginDto;
	}

	public Role getRoleDetails() {
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("E");
		return role;
	}

	/**
	 * This method is intended to test the LoginUser method.
	 */
	@Test
	public void testLoginUser() {
		Mockito.when(userRepository.findByMobileNumber(Mockito.anyString())).thenReturn(Optional.of(registration));
		Mockito.when(roleRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(role));
		LoginResponseDto response = loginService.loginUser(loginDto);
		Assert.assertEquals(loginResponseDto.getRegId(), response.getRegId());
	}
}
