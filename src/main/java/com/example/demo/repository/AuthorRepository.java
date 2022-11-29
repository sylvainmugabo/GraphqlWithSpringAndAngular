package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Author;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public Author findById(int id) {
        return authors.stream()
                .filter(author -> author.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));

    }

    public Author findByName(String name) {
        return authors.stream()
                .filter(author -> author.fullName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @PostConstruct
    private void init() {
        authors.add(new Author(1, "Josh", "Long"));
        authors.add(new Author(2, "Mark", "Heckler"));
        authors.add(new Author(3, "Greg", "Turnquist"));
    }

}
