package com.book.store.controller;

import com.book.store.db.entities.Student;
import com.book.store.request.StudentRequestDto;
import com.book.store.response.BaseResponse;
import com.book.store.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addStudent( @Valid @RequestBody StudentRequestDto studentRequestDto) {
        Student student = this.studentService.addStudent(studentRequestDto);
        BaseResponse response = new BaseResponse("Student added Successfully", "SUCCESS", student);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/get/{studentId}")
    public ResponseEntity<BaseResponse> getStudentById(@PathVariable("studentId") Integer studentId) {
        Student student = this.studentService.getStudentById(studentId);
        BaseResponse response = new BaseResponse("Fetched Student Successfully", "SUCCESS", student);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<BaseResponse> updateStudent(@PathVariable("studentId") Integer studentId, @Valid @RequestBody StudentRequestDto studentRequestDto) {
        Student student = this.studentService.updateStudent(studentId, studentRequestDto);
        BaseResponse response = new BaseResponse("Student Updated Successfully", "SUCCESS", student);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     *  Add to API's
     *      -> Activate/Deactive Student
     *      -> Delete Student
     *
     * */

}
