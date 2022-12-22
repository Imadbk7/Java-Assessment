package com.example.javaassignment.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Book {
    public enum Genres {
        FICTION,
        NONFICTION
    }

    @Id
    @GeneratedValue
    @Column(name = "bookId", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private Genres genre;
    
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "books")
    private List<Author> authors;
}
