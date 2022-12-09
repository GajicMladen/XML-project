package com.example.xmltim11.repository;

import com.example.xmltim11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmail(String email);
}
