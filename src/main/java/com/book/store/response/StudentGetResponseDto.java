package com.book.store.response;

import com.book.store.db.entities.Book;
import com.book.store.db.entities.Student;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentGetResponseDto {

    Student student;
    List<Book> books;

}
