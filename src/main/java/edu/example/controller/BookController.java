package edu.example.controller;


import edu.example.model.BookModel;
import edu.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    public final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('book', 'read')")
    public List<BookModel> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('book', 'read')")
    public BookModel findById(@PathVariable String id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasPermission('book', 'edit')")
    public BookModel create(@RequestBody BookModel bookModel) {
        return bookService.create(bookModel);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('book', 'edit')")
    public BookModel update(@RequestBody BookModel bookModel) {
        return bookService.update(bookModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission('book', 'delete')")
    public void delete(@PathVariable String id) {
        bookService.delete(id);
    }
}
