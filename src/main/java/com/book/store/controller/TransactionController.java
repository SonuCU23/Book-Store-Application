package com.book.store.controller;

import com.book.store.enums.TransactionType;
import com.book.store.response.BaseResponse;
import com.book.store.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    public ResponseEntity<BaseResponse> initiateTransaction(@RequestParam("studentId") Integer studentId,
                                                            @RequestParam("bookId") Integer bookId, @RequestParam("transactionType") TransactionType transactionType ) {

        BaseResponse response = this.transactionService.initiateTransaction(studentId, bookId, transactionType);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
