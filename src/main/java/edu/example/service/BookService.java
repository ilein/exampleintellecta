package edu.example.service;

import edu.example.model.BookModel;

import java.util.List;

public interface BookService {

    public List<BookModel> findAll();

    public BookModel findById(Object id);

    public BookModel create(BookModel bookModel);

    public BookModel update(BookModel bookModel);

    public void delete(Object id);
}
