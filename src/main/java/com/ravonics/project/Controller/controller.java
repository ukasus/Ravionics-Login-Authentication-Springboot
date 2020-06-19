package com.ravonics.project.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravonics.project.Dto.userDetail;
import com.ravonics.project.Entity.user;
import com.ravonics.project.Repo.UserRepository;

@Controller
public class controller {
	boolean errors=false;
	boolean creation=false;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value = "/")

	public String home() {
		return "index";

	}

	@RequestMapping(value = "/register")

	public String signUp() {
		return "signUp";

	}

	@RequestMapping(value = "/showLogin")

	public String showLogin(ModelMap modelMap) {
		if(errors)
		{
			modelMap.addAttribute("userNameError", "Invalid Username");
			modelMap.addAttribute("passwordError", "Invalid Password");
			errors=false;
		}
		else if(creation)
		{
			modelMap.addAttribute("accountCreation", "User Successfully Registered");
			creation=false;
		}
		return "login";

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(userDetail user, ModelMap modelMap,HttpServletResponse httpResponse) throws IOException {
		
		if (user.getUserName().length() >= 5) 
		{
			if (user.getPassword().length() >= 6) 
			{
				user saveUser = new user();
				saveUser.setUserName(user.getUserName());
				saveUser.setPassword(passwordEncoder.encode(user.getPassword()));

				userRepository.save(saveUser);
				creation=true;
				httpResponse.sendRedirect("/showLogin");
				
				return "login";
			} 
			else
				modelMap.addAttribute("passwordError", "Password should contains atleast 6 characters");
		} 
		else 
		{
			modelMap.addAttribute("userNameError", "Username should contains atleast 5 characters");
			if (user.getPassword().length() < 6) 
			{
				modelMap.addAttribute("passwordError", "Password should contains atleast 6 characters");
			}
		}

		return "signUp";

	}

	@RequestMapping("/hello")

	public String showHello() {
		return "Hello";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(userDetail user,HttpServletResponse httpResponse) throws IOException {
            
		if(user.getUserName().length()<5 || user.getPassword().length()<6)
            {
            	errors=true;
    			httpResponse.sendRedirect("/showLogin");
    		
    			return "login";
            }
		try 
		{
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
			Authentication auth=authenticationManager
					.authenticate(token);
			SecurityContext sc=SecurityContextHolder.getContext();
			
			
				sc.setAuthentication(auth);
			
			
		} 
		catch (Exception e) 
		{
			errors=true;
			httpResponse.sendRedirect("/showLogin");
		
			return "login";
		}
		httpResponse.sendRedirect("/hello");
		return "Hello";

	}

}
