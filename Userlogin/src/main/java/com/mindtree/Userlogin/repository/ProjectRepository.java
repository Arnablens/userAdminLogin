package com.mindtree.Userlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.Userlogin.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
