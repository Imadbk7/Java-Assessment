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
        persistAuthorBook(author);
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

    public void persistAuthorBook(Author author){
        if(author.getBooks() != null) {
            ArrayList<Author> authorArrayListh = new ArrayList<>();
            authorArrayListh.add(author);
            author.getBooks().stream().filter(book -> book.getAuthors() != null).forEach(book -> book.getAuthors().add(author));
            author.getBooks().stream().filter(book -> book.getAuthors() == null).forEach(book -> book.setAuthors(authorArrayListh));
        }
    }
}
