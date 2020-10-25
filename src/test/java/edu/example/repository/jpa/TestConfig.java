package edu.example.repository.jpa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"edu.example.repository"})
@EntityScan(basePackages = {"edu.example.model"})
public class TestConfig {

}
