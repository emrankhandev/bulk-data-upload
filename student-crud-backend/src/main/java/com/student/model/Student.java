package com.student.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Long id;
	
	@Column(name = "student_name")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "student_insertDate")
	private Date insertDate;
	
	@Column(name = "student_address")
	private String address;
	
	@Column(name = "student_bloodGroup")
	private String bloodGroup;
	
	@Column(name = "student_gender")
	private String gender;
	
	@Column(name = "student_education")
	private String education;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String name, String address, String bloodGroup, String gender, String education, Date dob, Date insertDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.bloodGroup = bloodGroup;
		this.gender = gender;
		this.education = education;
		this.insertDate = insertDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = new Date();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
}
