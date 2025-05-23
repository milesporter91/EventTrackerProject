package com.skilldistillery.books.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.books.entities.Book;
import com.skilldistillery.books.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public Book findById(int id) {
		return bookRepo.findById(id).orElse(null);
	}

	@Override
	public List<Book> findAll() {
		return bookRepo.findAll();
	}
}