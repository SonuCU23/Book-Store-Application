package com.book.store.service;

import com.book.store.adapter.StudentAdapter;
import com.book.store.db.entities.Student;
import com.book.store.db.repository.StudentRepository;
import com.book.store.request.StudentRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private Logger log = LoggerFactory.getLogger(BookService.class);


    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(StudentRequestDto studentRequestDto) {
        Student student = StudentAdapter.convertDtoToStudentEntity(studentRequestDto);
        return this.studentRepository.save(student);
    }

    public Student getStudentById(Integer studentId) {
        return this.studentRepository.findById(studentId).orElse(null);
    }

    public Student updateStudent(Integer studentId, StudentRequestDto studentRequestDto) {
        Optional<Student> student = this.studentRepository.findById(studentId);
        if(!student.isPresent()){
            log.error("No Book found for BookId: {}", studentId);
//            throw new BookException(HttpStatus.NOT_FOUND, "Book Id not found");
        }

        Student studentEntity = student.get();
        StudentAdapter.updateStudentEntity(studentEntity, studentRequestDto);
        Student updatedStudent = studentRepository.save(studentEntity);

        return updatedStudent;
    }
}
