package com.skilldistillery.books.services;

import java.util.List;

import com.skilldistillery.books.entities.Book;

public interface BookService {
	
	Book findById(int id);
	List<Book> findAll();

}
