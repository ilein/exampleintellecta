package edu.example.repository;

import edu.example.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    List<AuthorModel> findByLastName(String lastName);

    Optional<AuthorModel> findOneByLastName(String lastName);
}
