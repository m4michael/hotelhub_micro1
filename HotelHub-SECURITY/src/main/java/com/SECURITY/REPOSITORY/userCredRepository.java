package com.SECURITY.REPOSITORY;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SECURITY.ENTITY.ROLE;
import com.SECURITY.ENTITY.userCredential;

public interface userCredRepository extends JpaRepository<userCredential, String>{
	
		Optional<userCredential> findByEmail(String email);
	
		userCredential findByRole(ROLE role);

}
