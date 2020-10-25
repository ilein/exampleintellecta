package edu.example.repository.jpa;

import edu.example.model.AuthorModel;
import edu.example.repository.AuthorRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;


    @BeforeEach
    public void setUp() {
        //id сгенерируется сам, буду искать по фамилии
        AuthorModel author = new AuthorModel(0L, "Джон", "Рональд Руэл", "Толкин", Date.valueOf("1892-01-03"));
        authorRepository.save(author);
    }

    @Test
    public void createAuthorTest() {
        Optional<AuthorModel> authorSaved = authorRepository.findOneByLastName("Толкин");
        Assert.assertTrue(authorSaved.isPresent());
    }

    @Test
    public void findAllTest() {
        List<AuthorModel> authorModelList = authorRepository.findAll();
        Assert.assertEquals(authorModelList.size(), 3);
    }

    @Test
    public void findByAuthorLastName() {
        List<AuthorModel> authorModelList = authorRepository.findByLastName("Толкин");
        Assert.assertNotNull(authorModelList);
    }

    @AfterEach
    public void deleteAuthorTest() {
        Optional<AuthorModel> authorToDel = authorRepository.findOneByLastName("Толкин");
        Assert.assertTrue(authorToDel.isPresent());
        authorRepository.delete(authorToDel.get());
        List<AuthorModel> authorModelList = authorRepository.findAll();
        Assert.assertEquals(authorModelList.size(), 2);
    }
}