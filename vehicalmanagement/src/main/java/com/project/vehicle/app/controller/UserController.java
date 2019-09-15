package com.project.vehicle.app.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vehicle.app.dto.JwtResponse;
import com.project.vehicle.app.dto.LoginForm;
import com.project.vehicle.app.dto.ResponseMessage;
import com.project.vehicle.app.dto.SignUpForm;
import com.project.vehicle.app.model.Role;
import com.project.vehicle.app.model.RoleName;
import com.project.vehicle.app.model.User;
import com.project.vehicle.app.repository.RoleRepository;
import com.project.vehicle.app.repository.UserRepository;
import com.project.vehicle.app.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		System.out.println("****************************");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getOrganization(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		String strRoles = signUpRequest.getRole();

		if (strRoles == null || strRoles.isEmpty() || strRoles.equalsIgnoreCase("")) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> please select the roll"), HttpStatus.BAD_REQUEST);
		}
		Set<Role> role = new HashSet<>();

//		strRoles.forEach(role -> {
		switch (strRoles.toLowerCase()) {
		case "admin":
			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			role.add(adminRole);

			break;
		case "pm":
			Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			role.add(pmRole);

			break;
		default:
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			role.add(userRole);
		}
//		});

		user.setRoles(role);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}