package com.example.GraphqlWithSpringAndAngular.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.example.GraphqlWithSpringAndAngular.model.*;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public Author findById(int id) {
        return authors.stream()
                .filter(author -> author.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));

    }

    public List<Author> findAuthors() {
        return authors;
    }

    public Author findByName(String name) {
        return authors.stream()
                .filter(author -> author.fullName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public List<Book> books(Author author, BookRepository repo) {
        return repo.findAll().stream().filter(b -> b.authorId().equals(author.id())).toList();
    }

    @PostConstruct
    private void init() {
        authors.add(new Author(1, "Josh", "Long"));
        authors.add(new Author(2, "Mark", "Heckler"));
        authors.add(new Author(3, "Greg", "Turnquist"));
    }

}
