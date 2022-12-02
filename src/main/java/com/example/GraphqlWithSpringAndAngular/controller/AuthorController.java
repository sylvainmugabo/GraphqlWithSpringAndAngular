package com.example.GraphqlWithSpringAndAngular.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import com.example.GraphqlWithSpringAndAngular.model.*;
import com.example.GraphqlWithSpringAndAngular.repository.*;;

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;

    }

    @SchemaMapping(typeName = "Query", value = "findAuthors")
    public List<Author> findAuthors() {
        return authorRepository.findAuthors();
    }

    @SchemaMapping(typeName = "Query", value = "findById")
    public Author findById(@Argument Integer id) {
        return authorRepository.findById(id);
    }

    @SchemaMapping
    public List<Book> books(Author author) {
        return authorRepository.books(author, this.bookRepository);
    }

}
