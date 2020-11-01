package edu.example.service;

import edu.example.model.AuthorModel;

import java.util.List;

public interface AuthorService {

    public List<AuthorModel> findAll();

    public AuthorModel findById(Object id);

    public AuthorModel create(AuthorModel authorModel);

    public AuthorModel update(AuthorModel authorModel);

    public void delete(Object id);
}
