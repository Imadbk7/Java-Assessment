package com.example.javaassignment;

import com.example.javaassignment.models.Author;
import com.example.javaassignment.models.Book;
import com.example.javaassignment.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAssignmentApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Book b1 = new Book();
            Author author = new Author();
            author.setFirstName("Anne");
            author.setLastName("Frank");
            author.setDateOfBirth(LocalDate.of(1999, 6,12));
            b1.setTitle("Het achterhuis");
            List<Author> authors = new ArrayList<>();
            authors.add(author);
            b1.setAuthors(authors);
            b1.setGenre(Book.Genres.FICTION);
            bookRepository.save(b1);
        };
    }

}
