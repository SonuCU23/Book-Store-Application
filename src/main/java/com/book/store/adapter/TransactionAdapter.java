package com.book.store.adapter;

import com.book.store.db.entities.Book;
import com.book.store.db.entities.Student;
import com.book.store.db.entities.Transaction;
import com.book.store.enums.TransactionStatus;
import com.book.store.enums.TransactionType;
import com.book.store.response.BookIssuedResponse;

import java.util.UUID;

public class TransactionAdapter {

    public static Transaction createTransaction(Book book, Student student, TransactionType transactionType, TransactionStatus transactionStatus) {
        Transaction transaction = Transaction.builder()
                .externalTransactionId(UUID.randomUUID().toString())
                .book(book)
                .student(student)
                .transactionType(transactionType)
                .transactionStatus(transactionStatus)
                .build();

        return transaction;
    }
}
