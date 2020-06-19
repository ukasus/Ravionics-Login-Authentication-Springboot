package com.ravonics.project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravonics.project.Entity.user;


public interface UserRepository extends JpaRepository<user, Integer> {
	
	 user findByUserName(String userName);

}
