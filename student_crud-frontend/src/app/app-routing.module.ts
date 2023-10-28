import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateComponent } from './component/create/create.component';
import { StudentListComponent } from './component/student-list/student-list.component';
import { UpdateComponent } from './component/update/update.component';

const routes: Routes = [
  // {path: "", component: LoginComponent},
  {path: "add", component: CreateComponent},
  {path: "update/:id", component: UpdateComponent},
  {path: "list", component: StudentListComponent},
  {path: "", component: StudentListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
