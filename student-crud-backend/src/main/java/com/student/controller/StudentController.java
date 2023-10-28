package com.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.service.StudentService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/allStudents")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> student = studentService.getAllStudents();
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id){
		Student student = studentService.findStudentById(id);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student s = studentService.addStudent(student);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student){
		Student s = studentService.findStudentById(id);
		s.setName(student.getName());
		s.setAddress(student.getAddress());
		s.setBloodGroup(student.getBloodGroup());
		s.setGender(student.getGender());
		s.setEducation(student.getEducation());
		studentService.updateStudent(s);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/bulk-save")
	public ResponseEntity<Student> addStudent(@RequestBody List<Student> student){
		for (Student s: student)
		studentService.saveBulk(s);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/download/{filename}")
	public ResponseEntity<?> downloadFile(@PathVariable("filename") String fileName){
		Resource resource = resourceLoader.getResource("file:" + "/file/app_resource/my/data/" + fileName);
		try {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
					.body(resource);
		} catch (Exception e1) {
			e1.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}


}
