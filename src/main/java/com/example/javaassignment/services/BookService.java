package com.example.javaassignment.services;

import com.example.javaassignment.models.Book;
import com.example.javaassignment.repositories.AuthorRepository;
import com.example.javaassignment.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;


    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return this.bookRepository.findById(id);
    }

    public Book updateBook(Book book) {
        Optional<Book> book1 = this.bookRepository.findById(book.getId());
        book1.orElseThrow(() -> new IllegalStateException(
                "No book found with this id"));

        book1.ifPresent(book2 -> {
            // if book has no authors
            book2.getAuthors().forEach(author -> author.getBooks().remove(book2));
            persistAuthorBook(book);
        });

        return this.bookRepository.save(book);
    }


    public void persistAuthorBook(Book book) {
        if (book.getAuthors() != null) {
            ArrayList<Book> bookArrayList = new ArrayList<>();
            bookArrayList.add(book);
            book.getAuthors().stream().filter(author -> author.getBooks() != null).forEach(author -> author.getBooks().add(book));
            book.getAuthors().stream().filter(author -> author.getBooks() == null).forEach(author -> author.setBooks(bookArrayList));
        }
    }

    public Book addBook(Book book) {
        persistAuthorBook(book);
        return this.bookRepository.save(book);
    }

    public Book deleteBook(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "No book found with this id"));
        this.bookRepository.deleteById(id);
        return book;
    }


}
