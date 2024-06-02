package com.book.store.service;

import com.book.store.adapter.BookAdapter;
import com.book.store.db.entities.Author;
import com.book.store.db.entities.Book;
import com.book.store.db.repository.BookRepository;
import com.book.store.request.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;


    public Book addBook(BookRequestDto bookRequestDto) {

        Book book =  BookAdapter.convertDtoToBookEntity(bookRequestDto);

        // Saving Author details first
        Author author = book.getAuthor();
        author = this.authorService.getOrCreate(author);
        book.setAuthor(author);

        bookRepository.save(book);
        return book;
    }

    public Book getBookById(Integer bookId) {
        return this.bookRepository.findById(bookId).orElse(null);
    }

    public Book createOrUpdate(Book book){
        return this.bookRepository.save(book);
    }
}
