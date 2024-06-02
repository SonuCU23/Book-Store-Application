package com.book.store.service;

import com.book.store.adapter.TransactionAdapter;
import com.book.store.db.entities.Book;
import com.book.store.db.entities.Student;
import com.book.store.db.entities.Transaction;
import com.book.store.db.repository.BookRepository;
import com.book.store.db.repository.StudentRepository;
import com.book.store.db.repository.TransactionRepository;
import com.book.store.enums.TransactionStatus;
import com.book.store.enums.TransactionType;
import com.book.store.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    // TODO -> Avoid Using bookRepository and studentRepository in this class
    private Logger log = LoggerFactory.getLogger(TransactionService.class);


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Value("${students.books.max-allowed}")
    Integer maxAllowedBooks;

    public  BaseResponse initiateTransaction(Integer studentId, Integer bookId, TransactionType transactionType) {
        switch (transactionType) {
            case ISSUE ->
            {
                return initiateIssuance(studentId,bookId,transactionType);
            }
            case RETURN ->
            {
               return initiateReturn(studentId,bookId,transactionType);
            }
            default ->
                throw new RuntimeException("Unsupported transaction type");
        }
    }

    private  BaseResponse initiateIssuance(Integer studentId, Integer bookId, TransactionType transactionType) {

         Optional<Student> student = studentRepository.findById(studentId);
         if(student.isEmpty()) {
            log.error("Student with id {} not found", studentId);
            throw new RuntimeException("Student not found");
         }

        Student studentEntity = student.get();
        if(studentEntity.getBookList() == null || studentEntity.getBookList().size() > maxAllowedBooks) {
            log.error("student with id {} has issued maximum number {} of books allowed", studentId, maxAllowedBooks);
            throw new RuntimeException("student has issued maximum number");
        }

        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()) {
            log.error("Book with id {} not found", bookId);
            throw new RuntimeException("Book not found");
        }

        Book bookEntity = book.get();
        Transaction transaction = TransactionAdapter.createTransaction(bookEntity, studentEntity, transactionType, TransactionStatus.PENDING);
        try{

            // Assign Book to Student -> This can be done by using createOrUpdate Method using BookService
            bookEntity.setStudent(studentEntity);
            //book = this.bookService.createOrUpdate(book);.
             bookRepository.save(bookEntity);

            // Now updating transaction status
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);

            // Needs to improve response -> data { transactionId : -------- }
            return new BaseResponse("Book Issued Successfully", "SUCCESS", transaction.getExternalTransactionId());

        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);

            // Rollback -> retract Book from student
            if(bookEntity.getStudent() != null) {
                bookEntity.setStudent(null);
                bookRepository.save(bookEntity);
            }

            log.error("Error while Issuing Book: {}", e.getMessage());
            return new BaseResponse("Error while issuing book", "SUCCESS", null);

        }

    }

    private  BaseResponse initiateReturn(Integer studentId, Integer bookId, TransactionType transactionType) {
        return new BaseResponse("Error while issuing book", "SUCCESS", null);
    }
}
