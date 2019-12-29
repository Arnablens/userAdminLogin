package com.mindtree.Userlogin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.Userlogin.entity.Employee;
import com.mindtree.Userlogin.entity.Project;
import com.mindtree.Userlogin.exception.ControllerException;

@Service
public interface CompanyService {

	public void insertEmployee(Employee employee, int projectId) throws ControllerException;

	public String openPortal(int employeeId, String password) throws ControllerException;

	public void addProject(Project project);

	public List<Project> getProjects();

	public List<Employee> getAllUser();

	public List<Employee> getUserById(int employeeId) throws ControllerException;

	public List<Employee> viewAllTeamMembers(int eid);

	public String changePassword(String password, String password2, int eid) throws ControllerException;

}
