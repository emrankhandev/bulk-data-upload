package com.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

	Student findStudentById(Long id);
	void deleteStudentById(Long id);
	
}
