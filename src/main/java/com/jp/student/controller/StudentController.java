package com.jp.student.controller;

import com.jp.student.dto.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Jai",
                "Patel"
        );
        //return new ResponseEntity<>(student,HttpStatus.OK);
        return ResponseEntity
                .ok()
                .header("custom-header","trp")
                .body(student);
    }

    //http://localhost:8080/students
    @GetMapping
    public List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Jai", "Patel"));
        studentList.add(new Student(2, "Sameer", "Patel"));
        studentList.add(new Student(3, "Chintal","Patel"));
        return studentList;
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1
    @GetMapping("{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student(studentId, "Jai", "Patel");
    }

    @GetMapping("{id}/{first-name}/{last-name}")
    public Student studentPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName, lastName);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1
    @GetMapping("query")
    public Student studentRequestParam(@RequestParam int id){
        return new Student(id, "Jai", "Patel");
    }

    // http://localhost:8080/students/query?id=1&firstName=Jai&lastName=Patel
    @GetMapping("queries")
    public Student studentRequestParams(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    // Spring BOOT REST API for POST operation
    // http://localhost:8080/students/create
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println("Students id : " + student.getId());
        System.out.println("Students first name : " + student.getFirstName());
        System.out.println("Students last name : " + student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }


    // Spring BOOT REST API for PUT operation
    // http://localhost:8080/students/{id}/update
    @PutMapping("{id}/update")
    public Student updateStudents(@PathVariable("id") int studentId,
                                  @RequestBody Student student){
        System.out.println("Students first name : " + student.getFirstName());
        System.out.println("Students last name : " + student.getLastName());
        return student;
    }

    // Spring BOOT REST API for DELETE operation
    // http://localhost:8080/students/{id}/delete
    @DeleteMapping("{id}/delete")
    public String deleteStudents(@PathVariable("id") int studentsId){
        System.out.println("Students id : "+ studentsId);
        return "Successfully deleted!";
    }


}
