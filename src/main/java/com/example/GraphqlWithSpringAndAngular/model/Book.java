package com.example.GraphqlWithSpringAndAngular.model;

public record Book(Integer id, String title, Integer pages, Rating rating, Integer authorId) {

}
