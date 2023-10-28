package com.student.service;


import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dao.StudentRepo;
import com.student.model.Student;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public List<Student> getAllStudents(){
		return studentRepo.findAll();
	}
	
	public Student updateStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public Student findStudentById(Long id){
		return studentRepo.findStudentById(id);
	}
	
	public void deleteStudent(Long id) {
		studentRepo.deleteStudentById(id);
	}

	public Student saveBulk(Student body){
		return studentRepo.save(body);
	};
}
