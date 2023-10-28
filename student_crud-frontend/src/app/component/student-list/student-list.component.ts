import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudentService } from 'src/app/service/student.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[] = [];
  student: Student = new Student();
  constructor(private studentService: StudentService, private router: Router) { }

  ngOnInit(): void {
    this.getAllStudent();
  }

  getAllStudent() {
    this.studentService.getAllStudent().subscribe(data => {
      if (!data) alert("No Data found")
      this.students = data;
    })
  }

  update(id: any){
    this.router.navigate(['update', id]);
  }

  delete(id: any) {
    this.studentService.deleteStudent(id).subscribe(data => {
      this.student = data;
      this.getAllStudent();
      alert("student data delete successfully");
      }, error => alert("sorry unable to delete student data")
    )
  }

}
