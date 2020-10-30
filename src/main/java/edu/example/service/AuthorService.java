package edu.example.service;

import edu.example.exceptions.EntityAlreadyExistsException;
import edu.example.exceptions.EntityHasDetailException;
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
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<AuthorModel> findAll() {
        return authorRepository.findAll();
    }

    public AuthorModel findById(Object id) {
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

        Optional<AuthorModel> author = authorRepository.findById(parsedId);
        if (!author.isPresent()) {
            throw new EntityNotFoundException(AuthorModel.TYPE_NAME, parsedId);
        }
        return author.get();
    }

    public AuthorModel create(AuthorModel authorModel) {
        if (authorModel == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (authorModel.getLastName() == null || "".equals(authorModel.getLastName())) {
            throw new EntityIllegalArgumentException("Фамилия не может быть null");
        }
        if (authorModel.getFirstName() == null || "".equals(authorModel.getFirstName())) {
            throw new EntityIllegalArgumentException("Имя не может быть null");
        }
        Optional<AuthorModel> existedAuthor = authorRepository.findById(authorModel.getId());
        if (existedAuthor.isPresent()) {
            throw new EntityAlreadyExistsException(AuthorModel.TYPE_NAME, authorModel.getId());
        }
        return authorRepository.save(authorModel);
    }

    public void delete(Object id) {
        AuthorModel authorModel = findById(id);
        List<BookModel> bookModels = bookRepository.findByAuthorId(authorModel);
        if (bookModels.size() > 0) {
            throw new EntityHasDetailException(BookModel.TYPE_NAME, authorModel.getId());
        }
        authorRepository.delete(authorModel);
    }
}
