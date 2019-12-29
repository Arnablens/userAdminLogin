package com.mindtree.Userlogin.companyController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindtree.Userlogin.entity.Employee;
import com.mindtree.Userlogin.entity.Project;
import com.mindtree.Userlogin.exception.ControllerException;
import com.mindtree.Userlogin.service.CompanyService;

@Controller
public class CompanyController {
	public static int eid;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping("/")
	public String menu() {
		
		return "menu";
	}
	@RequestMapping("menu")
	public String menuFromLogo() {
		return "menu";
	}
	@RequestMapping("/signup")
	public String signUp()
	{
		return "addAdmin";
	}
	@RequestMapping("/signin")
	public String signIn()
	{
		return "signin";
	}
	@RequestMapping("/adminpage")
	public String adminPage()
	{
		return "openAdmin";
	}
	@RequestMapping("/userpage")
	public String userPage()
	{
		return "openUser";
	}
	@RequestMapping("/addproject")
	public String addProject() {
		return "addProject";
	}
	@RequestMapping("/getbyuserid")
	public String getUserById() {
		
		return "getByUserId";
	}
	@RequestMapping("/changePassword")
	public String updatePassword() {
		
		return "changePassword";
	}
	@PostMapping("/addEmployee")
	public String addEmployee(Employee employee, @RequestParam(value = "projectId", defaultValue = "0") int projectId) throws ControllerException {
		companyService.insertEmployee(employee, projectId );
		return "menu";
	}
	@RequestMapping("/employee")
	public String userOrAdmin(@RequestParam int employeeId, @RequestParam String password ) throws ControllerException {
		String result;	
		eid=employeeId;
		result=companyService.openPortal(employeeId,password);
		return result;
		
	}
	@PostMapping("/addProject")
	public String addProject(Project project) 
	{
		companyService.addProject(project);
		return "menu";
		
	}
	@GetMapping("/getAllProject")
	public String getProjects(Model model) {
		List<Project>projects=new ArrayList<Project>();
		projects=companyService.getProjects();
		model.addAttribute("projects", projects);
		return "getAllProject";
		
	}
	@GetMapping("/getAllUsers")
	public String getAllUser(Model model ) {
		
		List<Employee> employees=companyService.getAllUser();
		model.addAttribute("employees", employees);
		return "getallUsers";
		
	}
	
	@GetMapping("/getUserById")
	public String getUserById(Model model, @RequestParam int employeeId ) throws ControllerException {
		List<Employee> employees=companyService.getUserById(employeeId);
		model.addAttribute("employees", employees);
		return "getallUsers";
	
}
	@GetMapping("/viewAllTeamMembers")
	public String viewAllTeamMembers(Model model) {
		System.out.println(eid);
		List<Employee> employees=companyService.viewAllTeamMembers(eid);
		model.addAttribute("employees", employees);
		return "getallUsers";
	
}
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam String password, @RequestParam String password2) throws ControllerException {
		System.out.println(password);
		System.out.println(password2);
		String result=companyService.changePassword(password,password2,eid);
		return result;
		
	}
}
