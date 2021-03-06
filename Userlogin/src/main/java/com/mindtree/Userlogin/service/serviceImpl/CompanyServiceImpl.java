package com.mindtree.Userlogin.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.Userlogin.entity.Employee;
import com.mindtree.Userlogin.entity.Project;
import com.mindtree.Userlogin.exception.ControllerException;
import com.mindtree.Userlogin.repository.EmployeeRepository;
import com.mindtree.Userlogin.repository.ProjectRepository;
import com.mindtree.Userlogin.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public void insertEmployee(Employee employee, int projectId) throws ControllerException {
		if (employee.getRole().equals("admin")) {
			employeeRepository.save(employee);
		} else {

			Project project = projectRepository.getOne(projectId);
			if (project == null) {
				throw new ControllerException("no such project found exception ");

			} else {
				employee.setProject(project);
				employeeRepository.save(employee);
			}
		}
	}

	@Override
	public String openPortal(int employeeId, String password) throws ControllerException {
		Employee employee = employeeRepository.getOne(employeeId);
		if (employee.getEmployeeId() == employeeId && employee.getPassword().equals(password)
				&& employee.getRole().equals("admin")) {

			return "openAdmin";
		} else if (employeeId == employee.getEmployeeId() && employee.getPassword().equals(password)
				&& employee.getRole().equals("user")) {

			return "openUser";
		} else {
			throw new ControllerException("please check the user id or password incorrect details submitted ");
		}
	}
	
	@Override
	public void addProject(Project project) {
		// TODO Auto-generated method stub
		projectRepository.save(project);

	}

	@Override
	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		List<Project> projects = projectRepository.findAll();
		return projects;
	}

	@Override
	public List<Employee> getAllUser() {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public List<Employee> getUserById(int employeeId) throws ControllerException {
		// TODO Auto-generated method stub
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = employeeRepository.getOne(employeeId);
		List<Employee> listOfEmployees = employeeRepository.findAll();
		for (Employee employee2 : listOfEmployees) {
			if (employee2.getEmployeeId() == employeeId) {
				employees.add(employee);
				
			} 
		}
		if(employees.isEmpty()) {
			throw new ControllerException("no such user found exception");
		}
		return employees;
	}

	@Override
	public List<Employee> viewAllTeamMembers(int eid) {
		Employee employee = employeeRepository.getOne(eid);
		int pid = employee.getProject().getProjectId();
		System.out.println(pid);
		List<Employee> listOfEmployee = new ArrayList<Employee>();
		Project project = projectRepository.getOne(pid);
		listOfEmployee.addAll(project.getEmployees());
		return listOfEmployee;
	}

	@Override
	public String changePassword(String password, String password2, int eid) throws ControllerException {
		Employee employee = employeeRepository.getOne(eid);
		if (employee.getPassword().equals(password)) {
			employee.setPassword(password2);
			employeeRepository.saveAndFlush(employee);
			return "signin";
		} else {
			throw new ControllerException("password cannot be updated please check the old password ");
		}

	}

}
