package com.project.vehicle.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.vehicle.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);
}