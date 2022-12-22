package com.example.javaassignment.services;

import com.example.javaassignment.models.Book;
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
        return this.bookRepository.save(book);
    }

    public Book addBook(Book book) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(book);
        book.getAuthors().stream().map(data -> data).filter(data -> data.getBooks() != null).forEach(data -> data.getBooks().add(book));
        book.getAuthors().stream().map(data -> data).filter(data -> data.getBooks() == null).forEach(data -> data.setBooks(bookArrayList));
        return this.bookRepository.save(book);
    }

    public Book deleteBook(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "No book found with this id"));
        this.bookRepository.deleteById(id);
        return book;
    }


}
