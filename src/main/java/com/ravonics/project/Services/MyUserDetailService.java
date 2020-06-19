package com.ravonics.project.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ravonics.project.Entity.user;
import com.ravonics.project.Repo.UserRepository;

import org.springframework.security.core.userdetails.User;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user user=userRepository.findByUserName(username);
		return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
	}

}
