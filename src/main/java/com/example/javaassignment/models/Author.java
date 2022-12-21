package com.example.javaassignment.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "authorId", nullable = false)
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @Column(name = "date")
    private LocalDate dateOfBirth;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "authors")
    private List<Book> books;
}
