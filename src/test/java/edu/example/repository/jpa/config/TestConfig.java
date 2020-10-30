package edu.example.repository.jpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"edu.example.repository"})
@EntityScan(basePackages = {"edu.example.model"})
@ComponentScan(basePackages = {"edu.example.service"})
public class TestConfig {

}
