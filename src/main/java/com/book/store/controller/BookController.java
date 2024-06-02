package com.book.store.controller;

import com.book.store.request.BookRequestDto;
import com.book.store.response.BaseResponse;
import com.book.store.service.BookService;
import com.book.store.db.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addBook(@RequestBody BookRequestDto bookRequestDto) {
        Book book = this.bookService.addBook(bookRequestDto);
        BaseResponse response = new BaseResponse("Book added Successfully", "SUCCESS", book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<BaseResponse> getBookById(@PathVariable("bookId") Integer bookId) {
        Book book = this.bookService.getBookById(bookId);
        BaseResponse response = new BaseResponse("Fetched Book Successfully", "SUCCESS", book);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     *
     * TODO ->
     *      1). Get Book -> Implement Search Functionality on the basis of different parameters and Pagination
     *      2) . PUT / PATCH -> Updating Book details ( Admin )
     *      3). DELETE : Removing book from store ( Admin )
     *
     * **/

}
