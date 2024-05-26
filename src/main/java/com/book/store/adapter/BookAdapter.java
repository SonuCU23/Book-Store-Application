package com.book.store.adapter;

import com.book.store.db.entities.Author;
import com.book.store.db.entities.Book;
import com.book.store.enums.Genre;
import com.book.store.request.BookRequestDto;

public class BookAdapter {

    public static Book convertDtoToBookEntity(BookRequestDto bookRequestDto) {
        Book book = Book.builder()
                .bookName(bookRequestDto.getBookName())
                .genre(Genre.fromName(String.valueOf(bookRequestDto.getGenre())))
                .author(
                        Author.builder()
                                .name(bookRequestDto.getAuthorName())
                                .email(bookRequestDto.getAuthorEmail())
                                .build()
                )
                .price(bookRequestDto.getPrice())
                .build();

        return book;
    }
}
