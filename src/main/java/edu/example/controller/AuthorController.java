package edu.example.controller;

import edu.example.model.AuthorModel;
import edu.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorModel> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorModel findById(@PathVariable String id) {
        return authorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorModel create(@RequestBody AuthorModel authorModel) {
        return authorService.create(authorModel);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthorModel update(@RequestBody AuthorModel authorModel) {
        return authorService.update(authorModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        authorService.delete(id);
    }

}
