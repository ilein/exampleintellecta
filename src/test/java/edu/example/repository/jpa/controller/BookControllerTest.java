package edu.example.repository.jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.example.controller.BookController;
import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import edu.example.repository.jpa.service.mock.MockAuthorService;
import edu.example.repository.jpa.service.mock.MockBookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookController.class, MockBookService.class})
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    private MockMvc mockMvc;

    private static String URL = "http://localhost:8080/api/v1/book";

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(bookController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        AuthorModel author = new AuthorModel(1L,
                "Имя",
                "Отчество",
                "Фамилия",
                Date.valueOf("1980-01-02"));
        BookModel book = new BookModel(1L, "Название книги", 50, 1990, author);
        String requestJson = mapper.writeValueAsString(book);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(get(URL, 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        AuthorModel author = new AuthorModel(1L,
                "Имя",
                "Отчество",
                "Фамилия",
                Date.valueOf("1980-01-02"));
        BookModel book = new BookModel(1L, "Название книги", 50, 1990, author);
        String requestJson = mapper.writeValueAsString(book);
        mockMvc.perform(put(URL, 1).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
