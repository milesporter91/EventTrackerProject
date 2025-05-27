package com.skilldistillery.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.books.entities.Book;
import com.skilldistillery.books.services.BookService;

import jakarta.servlet.http.HttpServletRequest;
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
	
	@PostMapping({ "books", "books/" })
	public Book putForBook(@PathVariable("authorId") int authorId, 
			@RequestBody Book newBook, HttpServletResponse res, HttpServletRequest req) {
		try {
			newBook = bookService.create(authorId, newBook);
			if (newBook != null) {
				res.setStatus(HttpServletResponse.SC_CREATED);
				String url = req.getRequestURL().append("/").append(newBook.getId()).toString();
				res.setHeader("location", url);
			}
			else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			newBook = null;
		}
		return newBook;
	}
	
	@PutMapping("books/{bookId}")
	public Book updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book, HttpServletResponse res) {

		try {
			book = bookService.update(bookId, book);
			if (book == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return book;
	}
	
	@DeleteMapping("books/{bookId}")
	public void deleteFilm(@PathVariable("bookId") int bookId, HttpServletResponse res) {
		try {
			if (bookService.delete(bookId)) {
				res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
