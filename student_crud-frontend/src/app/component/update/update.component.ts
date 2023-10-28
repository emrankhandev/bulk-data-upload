import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudentService } from 'src/app/service/student.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  id!: number;
  student: Student = new Student();
  updateStudentForm!: FormGroup;
  constructor(private studentService: StudentService, private router: Router, private formBuilder: FormBuilder,private activeRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.updateStudentForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required, Validators.maxLength(20)]),
      address: new FormControl('', [Validators.required, Validators.maxLength(120)]),
      gender: new FormControl('', [Validators.required]),
      bloodGroup: new FormControl('', [Validators.required]),
      education: new FormControl('', [Validators.required,])
  })
    this.id = this.activeRouter.snapshot.params['id'];
      console.log(this.id);
      this.studentService.getStudentById(this.id).subscribe(data => {
        console.log(data)
        this.student = data;
  })
}
  updateStudent() {
    this.studentService.updateStudent(this.updateStudentForm.value, this.id).subscribe(data => {
      alert("Update /successfully");
      this.router.navigate(['list'])
  })
}

}
