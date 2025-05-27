package com.skilldistillery.books.services;

import java.util.List;

import com.skilldistillery.books.entities.Book;

public interface BookService {
	
	Book findById(int id);
	List<Book> findAll();
	Book create(Book newBook);
	Book update(int bookId, Book updatedBook);
	boolean delete(int bookId);

}
