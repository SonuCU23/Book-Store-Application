package com.book.store.db.entities;

import ch.qos.logback.classic.Logger;
import com.book.store.db.converter.TransactionTypeConverter;
import com.book.store.enums.TransactionStatus;
import com.book.store.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.book.store.db.converter.TransactionStatusConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String externalTransactionId;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"student", "transactionList"})
    private Book book;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList", "transactions"})
    private Student student;

    @Column(name = "status")
    @Convert(converter = TransactionStatusConverter.class)
    private TransactionStatus transactionStatus;

    @Column(name = "type")
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType transactionType;

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
    public Transaction(Integer id, String externalTransactionId, Book book, Student student, TransactionStatus transactionStatus, TransactionType transactionType) {
        this.id = id;
        this.externalTransactionId = externalTransactionId;
        this.book = book;
        this.student = student;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
    }
}
