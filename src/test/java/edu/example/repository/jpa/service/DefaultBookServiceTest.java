package edu.example.repository.jpa.service;

import edu.example.exceptions.EntityAlreadyExistsException;
import edu.example.exceptions.EntityIllegalArgumentException;
import edu.example.exceptions.EntityNotFoundException;
import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.repository.jpa.config.TestConfig;
import edu.example.service.impl.DefaultAuthorService;
import edu.example.service.impl.DefaultBookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultBookServiceTest {

    @Autowired
    private DefaultBookService defaultBookService;

    @Autowired
    private DefaultAuthorService defaultAuthorService;

    @Test
    public void findAllTest() {
        List<BookModel> bookModelList = defaultBookService.findAll();
        Assert.assertEquals(bookModelList.size(), 8);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findBookByNullId() {
        defaultBookService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findBookByNotLongId() {
        defaultBookService.findById("какой-то текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findBookByNotExistsId() {
        defaultBookService.findById(100L);
    }

    @Test
    public void findBookByExistId() {
        BookModel book = defaultBookService.findById(-1);
        Assert.assertNotNull(book);
        Assert.assertEquals(book.getName(), "Нос");
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createBookNullTest() {
        defaultBookService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createBookNameNull() {
        AuthorModel author = defaultAuthorService.findById(-1L);
        BookModel book = new BookModel(1L, null, 100, 2020, author);
        defaultBookService.create(book);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createBookPageCountNull() {
        AuthorModel author = defaultAuthorService.findById(-1L);
        BookModel book = new BookModel(1L, "Книга", null, 2020, author);
        defaultBookService.create(book);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorNull() {
        BookModel book = new BookModel(1L, "Книга", 100, 2020, null);
        defaultBookService.create(book);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createBookExistsTest() {
        AuthorModel author = defaultAuthorService.findById(-1L);
        BookModel book = new BookModel(-1L, "Книга", 100, 2020, author);
        defaultBookService.create(book);
    }

    @Test
    public void createBook() {
        AuthorModel author = defaultAuthorService.findById(-1L);
        BookModel book = new BookModel(1L, "Книга", 100, 2020, author);
        BookModel createdBook = defaultBookService.create(book);
        Assert.assertNotNull(createdBook);
        // было 8, стало 9?
        List<BookModel> bookModelList = defaultBookService.findAll();
        Assert.assertEquals(bookModelList.size(), 9);
        //удалить
        defaultBookService.delete(createdBook.getId());
    }

    /*можно не проверять, потому что поиск по id проверяется уже*/
    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteBookNullId() {
        defaultBookService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteBookNotLongId() {
        defaultBookService.delete("текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteBookNotExistId() {
        defaultBookService.delete(100L);
    }
    /*----------------*/

    @Test
    public void deleteBook() {
        /*создадим и удалим*/
        AuthorModel author = defaultAuthorService.findById(-1L);
        BookModel book = new BookModel(1L, "Книга", 100, 2020, author);
        BookModel createdBook = defaultBookService.create(book);
        defaultBookService.delete(createdBook.getId());
    }

}
