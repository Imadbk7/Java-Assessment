package com.example.javaassignment.controllers;

import com.example.javaassignment.models.Book;
import com.example.javaassignment.repositories.BookRepository;
import com.example.javaassignment.services.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

@Import({BookService.class, BookRepository.class})
@GraphQlTest(BookController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class BookControllerIntTest {


    public final GraphQlTester graphQlTester;

    @Test
    void testMethodBooksShouldFindAllBooks() {

        // language=GraphQL
        String document = """
                    query{
                      books{
                        id
                        title
                        genre
                        authors{
                          id
                          firstName
                          lastName
                          dateOfBirth
                        }
                      }
                    }          
                """;

        graphQlTester.document(document)
                .execute()
                .path("books")
                .entityList(Book.class)
                .hasSize(1);

    }

    @Test
    void testMethodfindBookByIdsShouldFindTheBook() {

        // language=GraphQL
        String document = """
                   query{
                     findBookById(id:1){
                       id
                       title
                       genre
                     authors{
                       id
                       lastName
                     }
                     }
                   }       
                """;

        graphQlTester.document(document)
                .variable("id", 1)
                .execute()
                .path("findBookById")
                .entity(Book.class)
                .satisfies(Book -> {
                    assertEquals("Het achterhuis", Book.getTitle());
                });

    }

}
