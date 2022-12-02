package com.example.GraphqlWithSpringAndAngular.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.example.GraphqlWithSpringAndAngular.model.Author;
import com.example.GraphqlWithSpringAndAngular.model.Book;
import com.example.GraphqlWithSpringAndAngular.model.Rating;

@Repository
public class BookRepository {
    private final AuthorRepository authorRepository;

    private List<Book> books = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findOne(Integer id) {
        return books.stream()
                .filter(book -> book.id() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(String title, Integer page, Integer ratingNumber, Integer authorId) {
        Integer id = books.stream()
                .mapToInt(v -> v.id())
                .max().orElse(0);
        Rating rating = mapRatingNumberToRatingEnum(ratingNumber);

        Book book = new Book(id + 1, title, page, rating, authorId);
        books.add(book);
        return book;

    }

    public Author findAuthor(Book book) {
        return authorRepository.findById(book.id());
    }

    private Rating mapRatingNumberToRatingEnum(Integer ratingNumber) {
        Rating rate = Rating.UNKOWN;
        switch (ratingNumber) {
            case 1:
                rate = Rating.ONE_STAR;
                break;
            case 2:
                rate = Rating.TWO_STARS;
                break;
            case 3:
                rate = Rating.THREE_STARS;
                break;
            case 4:
                rate = Rating.FOUR_STARS;
                break;
            case 5:
                rate = Rating.FIVE_STARS;
                break;
            default:
                break;
        }
        return rate;
    }

    @PostConstruct
    private void init() {
        books.add(
                new Book(1, "Reactive Spring", 484, Rating.FIVE_STARS, authorRepository.findByName("Josh Long").id()));
        books.add(new Book(2,
                "Spring Boot Up & Running",
                328,
                Rating.THREE_STARS,
                authorRepository.findByName("Mark Heckler").id()));
        books.add(new Book(3,
                "Hacking with Spring Boot 2.3",
                392,
                Rating.ONE_STAR,
                authorRepository.findByName("Greg Turnquist").id()));
    }

}
