import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { StudentService } from 'src/app/service/student.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  student: Student = new Student();
  constructor(private router: Router, private studentService: StudentService, private formBuilder: FormBuilder) { }
  studentForm!: FormGroup;
  records: Student[] = [];

  ngOnInit(): void {
    this.studentForm = this.formBuilder.group({
      file: new FormControl(''),
    })
  }

  get f() {
    return this.studentForm.controls;
  }

  addStudent() {
    this.studentService.saveBulk(this.records).subscribe(data => {
      alert("Successfully saved!")
      this.studentList();
    }, () => alert("Something went wrong"))
  }

  studentList() {
    this.router.navigate(['/'])
  }

  onFileSelect(fileInputNode: any): any {

    let csvFile = fileInputNode.files[0];
    let reader: FileReader = new FileReader();

    reader.readAsText(csvFile);
    reader.onload = () => {

      let csvRawData = reader.result;
      let csvDataList = (<string>csvRawData).split(/\r\n|\n/);

      /* getting header & records from the csv */
      let headersRow = this.getHeaderList(csvDataList);
      this.records = this.getDataListFromCSVFile(csvDataList, headersRow.length);
    }

  }

  getHeaderList(dataList: any) {
    let headers = (<string>dataList[0]).split('\t');
    let headerList = [];
    for (let j = 0; j < headers.length; j++) {
      headerList.push(headers[j]);
    }
    return headerList;
  }

  getDataListFromCSVFile(dataList: any, headerLength: any) {

    let modelDataList = [];
    for (let i = 1; i < dataList.length; i++) {
      let currentRecord = (<string>dataList[i]).split('\t');
      if (currentRecord.length == headerLength) {
        let model: Student = new Student();
        model.name = currentRecord[0].trim();
        model.address = currentRecord[1].trim();
        model.gender = currentRecord[2].trim();
        model.bloodGroup = currentRecord[3].trim();
        model.education = currentRecord[4].trim();
        modelDataList.push(model);
      }
    }
    return modelDataList;
  }


}
