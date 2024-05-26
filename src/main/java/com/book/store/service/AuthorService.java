package com.book.store.service;

import com.book.store.db.entities.Author;
import com.book.store.db.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getOrCreate(Author author){
        Author savedAuthor = this.authorRepository.findByEmail(author.getEmail());

        if(savedAuthor == null){
            author = this.authorRepository.save(author);
            return author;
        }

        return savedAuthor;
    }
}
