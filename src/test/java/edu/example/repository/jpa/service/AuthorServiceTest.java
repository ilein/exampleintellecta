package edu.example.repository.jpa.service;

import edu.example.exceptions.EntityAlreadyExistsException;
import edu.example.exceptions.EntityHasDetailException;
import edu.example.exceptions.EntityIllegalArgumentException;
import edu.example.exceptions.EntityNotFoundException;
import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.repository.jpa.config.TestConfig;
import edu.example.service.AuthorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void findAllTest() {
        List<AuthorModel> authorModelList = authorService.findAll();
        Assert.assertEquals(authorModelList.size(), 2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findAuthorByNullId() {
        authorService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findAuthorByNotLongId() {
        authorService.findById("внезапный текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findAuthorByNotExistId() {
        authorService.findById(100L);
    }

    @Test
    public void findAuthorByExistId() {
        AuthorModel authorModel = authorService.findById(-1L);
        Assert.assertEquals(authorModel.getLastName(), "Гоголь");
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorNullTest() {
        authorService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorLastNameNull() {
        AuthorModel author = new AuthorModel(-1, "Имя", "Отчество", null, Date.valueOf("1980-01-20"));
        authorService.create(author);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorFirstNameNull() {
        AuthorModel author = new AuthorModel(-1, null, "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        authorService.create(author);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAuthorExistsTest() {
        AuthorModel author = new AuthorModel(-1, "Имя", "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        authorService.create(author);
    }

    @Test
    public void createAuthorTest() {
        AuthorModel author = new AuthorModel(1L, "Имя", "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        AuthorModel createdAuthor = authorService.create(author);
        Assert.assertNotNull(createdAuthor);
        // было 2, стало 3
        List<AuthorModel> authotModelList = authorService.findAll();
        Assert.assertEquals(authotModelList.size(), 3);
        //удалить
        authorService.delete(createdAuthor.getId());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteByNullId() {
        authorService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteByTextId() {
        authorService.delete("текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteByNotExistId() {
        authorService.delete(100L);
    }

    @Test(expected = EntityHasDetailException.class)
    public void deleteByHasDetailException() {
        authorService.delete(-1L);
    }

    @Test
    public void deleteTest() {
        AuthorModel author = new AuthorModel(1L, "Имя", "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        AuthorModel createdAuthor = authorService.create(author);
        authorService.delete(createdAuthor.getId());
    }

}
