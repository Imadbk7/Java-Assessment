package com.example.javaassignment.controllers;

import com.example.javaassignment.models.Author;
import com.example.javaassignment.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    public final AuthorService authorService;

    @MutationMapping
    public Author addAuthor(@Argument Author author) {
        return this.authorService.addAuthor(author);
    }

    @QueryMapping
    public List<Author> authors() {
        return this.authorService.getAllAuthors();
    }

    @MutationMapping
    public Optional<Author> deleteAuthor(@Argument Long id) {
        return this.authorService.deleteAuthor(id);
    }

    @QueryMapping
    public Optional<Author> findAuthorById(@Argument Long id) {
        return this.authorService.findAuthorById(id);
    }
}
