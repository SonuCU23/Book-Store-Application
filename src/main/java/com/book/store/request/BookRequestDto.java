package com.book.store.request;

import com.book.store.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class BookRequestDto {

    @NotBlank(message = "Book Name must not be blank")
    private String bookName;

    @NotNull(message = "Book Genre must not be blank")
    private Genre genre;

    @NotBlank(message = "Author Name not be blank")
    private String authorName;

    @NotBlank(message = "Author Email must not be blank")
    private String authorEmail;

    @NotBlank(message = "Price must not be blank")
    private double price;

}
