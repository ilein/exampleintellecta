package edu.example.repository.jpa.service;

import edu.example.exceptions.EntityAlreadyExistsException;
import edu.example.exceptions.EntityHasDetailException;
import edu.example.exceptions.EntityIllegalArgumentException;
import edu.example.exceptions.EntityNotFoundException;
import edu.example.model.AuthorModel;
import edu.example.repository.jpa.config.TestConfig;
import edu.example.service.impl.DefaultAuthorService;
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
public class DefaultAuthorServiceTest {

    @Autowired
    private DefaultAuthorService defaultAuthorService;

    @Test
    public void findAllTest() {
        List<AuthorModel> authorModelList = defaultAuthorService.findAll();
        Assert.assertEquals(authorModelList.size(), 2);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findAuthorByNullId() {
        defaultAuthorService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findAuthorByNotLongId() {
        defaultAuthorService.findById("внезапный текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findAuthorByNotExistId() {
        defaultAuthorService.findById(100L);
    }

    @Test
    public void findAuthorByExistId() {
        AuthorModel authorModel = defaultAuthorService.findById(-1L);
        Assert.assertEquals(authorModel.getLastName(), "Гоголь");
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorNullTest() {
        defaultAuthorService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorLastNameNull() {
        AuthorModel author = new AuthorModel(-1, "Имя", "Отчество", null, Date.valueOf("1980-01-20"));
        defaultAuthorService.create(author);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorFirstNameNull() {
        AuthorModel author = new AuthorModel(-1, null, "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        defaultAuthorService.create(author);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createAuthorExistsTest() {
        AuthorModel author = new AuthorModel(-1, "Имя", "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        defaultAuthorService.create(author);
    }

    @Test
    public void createAuthorTest() {
        AuthorModel author = new AuthorModel(1L, "Имя", "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        AuthorModel createdAuthor = defaultAuthorService.create(author);
        Assert.assertNotNull(createdAuthor);
        // было 2, стало 3
        List<AuthorModel> authotModelList = defaultAuthorService.findAll();
        Assert.assertEquals(authotModelList.size(), 3);
        //удалить
        defaultAuthorService.delete(createdAuthor.getId());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteByNullId() {
        defaultAuthorService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteByTextId() {
        defaultAuthorService.delete("текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteByNotExistId() {
        defaultAuthorService.delete(100L);
    }

    @Test(expected = EntityHasDetailException.class)
    public void deleteByHasDetailException() {
        defaultAuthorService.delete(-1L);
    }

    @Test
    public void deleteTest() {
        AuthorModel author = new AuthorModel(1L, "Имя", "Отчество", "Фамилия", Date.valueOf("1980-01-20"));
        AuthorModel createdAuthor = defaultAuthorService.create(author);
        defaultAuthorService.delete(createdAuthor.getId());
    }

}
