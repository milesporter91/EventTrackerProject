package com.skilldistillery.books.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.books.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

	List<Author> findByLastName(String lastName);
}
