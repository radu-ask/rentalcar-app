package com.springboot.rentapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.rentapp.entity.User;
import com.springboot.rentapp.service.UserService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private UserService userService;
	
	public EmployeeController(UserService theUserService) {
		userService = theUserService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<User> theUsers = userService.findAll();
		
		theModel.addAttribute("employees", theUsers);
		
		return "/employees/list-employees";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") Long theId) {
		
		userService.deleteById(theId);
		
		System.out.println("In method delete");
		
		return "redirect:/employees/list";
	}
}