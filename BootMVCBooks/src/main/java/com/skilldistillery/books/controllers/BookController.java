package com.skilldistillery.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.books.entities.Book;
import com.skilldistillery.books.services.BookService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping({ "books", "books/" })
	public List<Book> index() {
		return bookService.findAll();
	}
	@GetMapping("books/{bookId}")
	public Book findById(@PathVariable("bookId") int bookId, HttpServletResponse res) {
		Book book = bookService.findById(bookId);
		if (book == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return book;
	}

}
