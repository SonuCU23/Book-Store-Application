package com.book.store.db.entities;

import com.book.store.db.converter.GenreConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.book.store.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "book")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_name")
    private String bookName;


    @Column(name = "genre")
    @Convert(converter = GenreConverter.class)
    private Genre genre;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties({"bookList", "createdAt"}) // to avoid infinite recursion { Ignoring the bookList attribute in the Author }
    private Author author;          // cyclic dependency

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"bookList", "createdAt, updatedAt"}) // { Ignoring the bookList attribute in the Student }
    private Student student;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book") // to avoid infinite recursion { Ignoring the book attribute in the TransactionList }
    private List<Transaction> transactionList;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Book(Integer id, String bookName, Genre genre, double price, Author author, Student student ) {
        this.id = id;
        this.bookName = bookName;
        this.genre = genre;
        this.price = price;
        this.author = author;
        this.student = student;
    }
}
