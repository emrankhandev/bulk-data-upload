import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../model/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  apiBaseURL = "http://localhost:8080/api/v1";
  constructor(private httpClient: HttpClient) { }

  //save
  saveStudent(student: Student): Observable<Student> {
    return this.httpClient.post(`${this.apiBaseURL}/save`, student)
  }

  //get all
  getAllStudent(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(`${this.apiBaseURL}/allStudents`);
  }

  //get singel student
  getStudentById(id: number): Observable<Student> {
    return this.httpClient.get<Student>(`${this.apiBaseURL}/student/${id}`);
  }

//update
  updateStudent(student: Student, id: number): Observable<Student> {
    return this.httpClient.put<Student>(`${this.apiBaseURL}/update/${id}`, student);
  }

//delete
  deleteStudent(id: number): Observable<Student> {
    return this.httpClient.delete<Student>(`${this.apiBaseURL}/delete/${id}`);
  }

  saveBulk(student: Student[]): Observable<Student> {
    return this.httpClient.post(`${this.apiBaseURL}/bulk-save`, student)
  }

}
