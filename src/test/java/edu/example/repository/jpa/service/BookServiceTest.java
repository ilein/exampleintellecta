package edu.example.repository.jpa.service;

import edu.example.exceptions.EntityAlreadyExistsException;
import edu.example.exceptions.EntityIllegalArgumentException;
import edu.example.exceptions.EntityNotFoundException;
import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.repository.jpa.config.TestConfig;
import edu.example.service.AuthorService;
import edu.example.service.BookService;
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
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void findAllTest() {
        List<BookModel> bookModelList = bookService.findAll();
        Assert.assertEquals(bookModelList.size(), 8);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findBookByNullId() {
        bookService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findBookByNotLongId() {
        bookService.findById("какой-то текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findBookByNotExistsId() {
        bookService.findById(100L);
    }

    @Test
    public void findBookByExistId() {
        BookModel book = bookService.findById(-1);
        Assert.assertNotNull(book);
        Assert.assertEquals(book.getName(), "Нос");
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createBookNullTest() {
        bookService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createBookNameNull() {
        AuthorModel author = authorService.findById(-1L);
        BookModel book = new BookModel(1L, null, 100, 2020, author);
        bookService.create(book);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createBookPageCountNull() {
        AuthorModel author = authorService.findById(-1L);
        BookModel book = new BookModel(1L, "Книга", null, 2020, author);
        bookService.create(book);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createAuthorNull() {
        BookModel book = new BookModel(1L, "Книга", 100, 2020, null);
        bookService.create(book);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void createBookExistsTest() {
        AuthorModel author = authorService.findById(-1L);
        BookModel book = new BookModel(-1L, "Книга", 100, 2020, author);
        bookService.create(book);
    }

    @Test
    public void createBook() {
        AuthorModel author = authorService.findById(-1L);
        BookModel book = new BookModel(1L, "Книга", 100, 2020, author);
        BookModel createdBook = bookService.create(book);
        Assert.assertNotNull(createdBook);
        // было 8, стало 9?
        List<BookModel> bookModelList = bookService.findAll();
        Assert.assertEquals(bookModelList.size(), 9);
        //удалить
        bookService.delete(createdBook.getId());
    }

    /*можно не проверять, потому что поиск по id проверяется уже*/
    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteBookNullId() {
        bookService.delete(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void deleteBookNotLongId() {
        bookService.delete("текст");
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteBookNotExistId() {
        bookService.delete(100L);
    }
    /*----------------*/

    @Test
    public void deleteBook() {
        /*создадим и удалим*/
        AuthorModel author = authorService.findById(-1L);
        BookModel book = new BookModel(1L, "Книга", 100, 2020, author);
        BookModel createdBook = bookService.create(book);
        bookService.delete(createdBook.getId());
    }

}
