package edu.example.repository.jpa.service.mock;

import edu.example.model.AuthorModel;
import edu.example.service.AuthorService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class MockAuthorService implements AuthorService {
    @Override
    public List<AuthorModel> findAll() {
        return new ArrayList<>();
    }

    @Override
    public AuthorModel findById(Object id) {
        return new AuthorModel(Long.parseLong(String.valueOf(id)),
                "Имя",
                "Отчество",
                "Фамилия",
                Date.valueOf("1980-01-02"));
    }

    @Override
    public AuthorModel create(AuthorModel authorModel) {
        return authorModel;
    }

    @Override
    public AuthorModel update(AuthorModel authorModel) {
        return authorModel;
    }

    @Override
    public void delete(Object id) {

    }
}
