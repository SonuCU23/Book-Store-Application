package com.book.store.adapter;

import com.book.store.db.entities.Student;
import com.book.store.request.StudentRequestDto;

public class StudentAdapter {

    public static Student convertDtoToStudentEntity(StudentRequestDto studentRequestDto) {
        Student student = Student.builder()
                .name(studentRequestDto.getName())
                .email(studentRequestDto.getEmailId())
                .mobile(studentRequestDto.getMobile())
                .build();

        return student;
    }

    public static void updateStudentEntity(Student student, StudentRequestDto studentRequestDto) {
        if(studentRequestDto.getName() != null) {
            student.setName(studentRequestDto.getName());
        }
        if(studentRequestDto.getEmailId() != null) {
            student.setEmail(studentRequestDto.getEmailId());
        }
        if(studentRequestDto.getMobile() != null) {
            student.setMobile(studentRequestDto.getMobile());
        }
    }

}
