package com.example.javaassignment.services;

import com.example.javaassignment.models.Author;
import com.example.javaassignment.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(Long id) {
        return this.authorRepository.findById(id);
    }

    public Optional<Author> deleteAuthor(Long id) {
        Optional<Author> author = this.findAuthorById(id);
        author.orElseThrow(() -> new IllegalStateException("no author with this id found"));
        author.ifPresent(this.authorRepository::delete);
        return author;
    }
}
