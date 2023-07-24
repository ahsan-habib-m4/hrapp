package com.leads.hrapp.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leads.hrapp.model.Employee;
import com.leads.hrapp.model.Employees;
import com.leads.hrapp.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeController {
	
	@Autowired EmployeeService empService;
	
	@GetMapping
	public String show(Model model) {
		
		List <Employee> employees = (List<Employee>) empService.findAll();
		
		model.addAttribute("employees", employees);
		
		return "employes";
		
	}
	
	@GetMapping( value={"/employee/{empoyeeId}", "/employee"})
	public String updateEmployee(@PathVariable(name = "empoyeeId", required = false) Integer empoyeeId, Model model) {
		
		Employee employee =  new Employee();
		if (empoyeeId != null )
			employee = empService.findById(empoyeeId);
		model.addAttribute("employee", employee);
		
		return "employee";
		
	}
	
	@PostMapping("/save")
	public String updateEmployee(@ModelAttribute Employee employee, Model model) {
		
		empService.save(employee);	
		
		return "redirect:/";
		
	}
	
	@GetMapping("/delete/{empoyeeId}")
	public String deleteEmployee(@PathVariable(name = "empoyeeId") int empoyeeId, Model model) {
		
		empService.deleteById(empoyeeId);	
		
		return "redirect:/";
		
	}
	
	@PostMapping("/upload")
    public String singleFileUpload(@RequestParam("fileInput") MultipartFile fileInput, RedirectAttributes redirectAttributes) {

		if (fileInput.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		
		try {
			InputStreamReader imr = new InputStreamReader(fileInput.getInputStream(), StandardCharsets.UTF_8);
			XmlMapper  xmlMapper = new XmlMapper();
			Employees employees = xmlMapper.readValue(imr, Employees.class);
			
			for (Employee employee : employees.getEmployees()) {
				
				Employee exist = empService.findById(employee.getId());
				
				if (exist != null) {
					empService.save(employee);
				} else {
					empService.upload(employee);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

}
