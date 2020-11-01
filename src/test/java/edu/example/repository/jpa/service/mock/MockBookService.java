package edu.example.repository.jpa.service.mock;

import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.service.BookService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MockBookService implements BookService {
    @Override
    public List<BookModel> findAll() {
        return new ArrayList<>();
    }

    @Override
    public BookModel findById(Object id) {
        AuthorModel author = new AuthorModel(Long.parseLong(String.valueOf(id)),
                "Имя",
                "Отчество",
                "Фамилия",
                Date.valueOf("1980-01-02"));
        return new BookModel(1L, "Название книги", 50, 1990, author);
    }

    @Override
    public BookModel create(BookModel bookModel) {
        return bookModel;
    }

    @Override
    public BookModel update(BookModel bookModel) {
        return bookModel;
    }

    @Override
    public void delete(Object id) {

    }
}
