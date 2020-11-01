package edu.example.controller;

import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.repository.AuthorRepository;
import edu.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v0")
public class TestController {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Autowired
    public TestController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }

    @GetMapping("/authors/jpa")
    public List<AuthorModel> getAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping("/authors/jpa")
    public AuthorModel addAuthor(@RequestBody AuthorModel authorModel) {
        return authorRepository.save(authorModel);
    }

    @GetMapping("/books/jpa")
    public List<BookModel> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/books/jpa")
    public BookModel addBook(@RequestBody BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    @GetMapping("/books/jpa/old/{year}")
    public List<BookModel> getBooksYearLessThen(@PathVariable(name = "year") Integer year) {
        return bookRepository.findByPublishYearLessThan(year);
    }

    @DeleteMapping("/books/jpa/{id}")
    public Boolean delBook(@PathVariable(name = "id") long id) {
        bookRepository.deleteById(id);
        return true;
    }

    @DeleteMapping("/authors/jpa/{id}")
    public Boolean delAuthor(@PathVariable(name = "id") long id) {
        authorRepository.deleteById(id);
        return true;
    }
}
