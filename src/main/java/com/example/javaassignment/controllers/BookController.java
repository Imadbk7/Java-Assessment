package com.example.javaassignment.controllers;

import com.example.javaassignment.models.Author;
import com.example.javaassignment.models.Book;
import com.example.javaassignment.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    public final BookService bookService;

    @QueryMapping
    public List<Book> books() {
        return this.bookService.getAllBooks();
    }

    @QueryMapping
    public Optional<Book> findBookById(@Argument Long id) {
        return this.bookService.getBookById(id);
    }

    @MutationMapping
    public Book addBook(@Argument Book book) {
        return this.bookService.addBook(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Book book) {
        return this.bookService.updateBook(book);
    }

    @MutationMapping
    public Book deleteBook(@Argument Long id) {
        return this.bookService.deleteBook(id);
    }

}
