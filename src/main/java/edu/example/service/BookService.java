package edu.example.service;

import edu.example.exceptions.EntityAlreadyExistsException;
import edu.example.exceptions.EntityIllegalArgumentException;
import edu.example.exceptions.EntityNotFoundException;
import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.repository.AuthorRepository;
import edu.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    public BookModel findById(Object id) {
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Long parsedId;
        try {
            parsedId = Long.valueOf(String.valueOf(id));
        }
        catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(
                    String.format("Не удалось преобразовать индентификатор к нужному типу, текст ошибки: %s", ex));
        }

        Optional<BookModel> book = bookRepository.findById(parsedId);
        if (!book.isPresent()) {
            throw new EntityNotFoundException(BookModel.TYPE_NAME, parsedId);
        }
        return book.get();
    }

    public BookModel create(BookModel bookModel) {
        if (bookModel == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (bookModel.getName() == null || "".equals(bookModel.getName())) {
            throw new EntityIllegalArgumentException("Наименование не может быть null");
        }
        if (bookModel.getPagesCount() == null) {
            throw new EntityIllegalArgumentException("Количество страниц не может быть null");
        }
        if (bookModel.getAuthorId() == null) {
            throw new EntityIllegalArgumentException("Автор не может быть null");
        }
        Optional<BookModel> existedBook = bookRepository.findById(bookModel.getId());
        if (existedBook.isPresent()) {
            throw new EntityAlreadyExistsException(BookModel.TYPE_NAME, bookModel.getId());
        }
        return bookRepository.save(bookModel);
    }

    public void delete(Object id) {
        BookModel bookModel = findById(id);
        bookRepository.delete(bookModel);
    }

}
