package com.book.store.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "author")
@NoArgsConstructor
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;


    @OneToMany(mappedBy = "author") // attribute name of the forward directional FK attribute
    @JsonIgnoreProperties("author") // to avoid infinite recursion ( Ignoring the author attribute in the BookList )
    private List<Book> bookList;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Author(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
