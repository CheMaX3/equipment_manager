package com.chemax.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
    }
}

	//TODO:найти как происходит коннект к БД
	//TODO:написать приложение для собаки. Контроллер может забирать и класть собаку в базу на чистом спринге.
	//spring core
