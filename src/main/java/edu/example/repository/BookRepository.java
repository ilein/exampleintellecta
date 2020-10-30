package edu.example.repository;

import edu.example.model.AuthorModel;
import edu.example.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    List<BookModel> findByPublishYearLessThan(Integer year);

    List<BookModel> findByAuthorId(AuthorModel authorModel);
}
