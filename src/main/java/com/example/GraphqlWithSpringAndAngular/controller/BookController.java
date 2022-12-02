package com.example.GraphqlWithSpringAndAngular.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import com.example.GraphqlWithSpringAndAngular.model.*;
import com.example.GraphqlWithSpringAndAngular.repository.*;;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @SchemaMapping(typeName = "Query", value = "allBooks")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @SchemaMapping(typeName = "Query", value = "findOne")
    public Book findOne(@Argument Integer id) {
        return bookRepository.findOne(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return this.authorRepository.findById(book.authorId());
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument Integer pages, @Argument Integer rating,
            @Argument Integer authorId) {
        return this.bookRepository.createBook(title, pages, rating, authorId);

    }

}
