package com.book.store.response;

import com.book.store.db.entities.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookIssuedResponse {

    private String TransactionId;

}
