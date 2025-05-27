package com.skilldistillery.books.services;

import java.util.List;

import com.skilldistillery.books.entities.Author;

public interface AuthorService {
	Author findById(int id);
	List<Author> findAll();
	Author create(Author newAuthor);
	Author update(int authorId, Author updatedAuthor);
	boolean delete(int authorId);

}
