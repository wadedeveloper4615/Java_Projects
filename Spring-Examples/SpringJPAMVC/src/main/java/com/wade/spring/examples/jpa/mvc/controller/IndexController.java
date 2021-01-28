package com.wade.spring.examples.jpa.mvc.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wade.spring.examples.jpa.mvc.exception.RecordNotFoundException;
import com.wade.spring.examples.jpa.mvc.model.Employee;
import com.wade.spring.examples.jpa.mvc.service.EmployeeService;

@Controller
public class IndexController {
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private EmployeeService service;

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(Employee employee) {
		service.createOrUpdateEmployee(employee);
		return "redirect:/";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			Employee entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new Employee());
		}
		return "add-edit-employee";
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("employees", service.getAllEmployees());
		return "index";
	}
}
