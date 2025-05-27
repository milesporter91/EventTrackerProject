package com.skilldistillery.books.services;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Book create( Book newBook) {
			bookRepo.saveAndFlush(newBook);
			return newBook;
	}

	@Override
	public Book update(int bookId, Book updatedBook) {
		Optional<Book> bookOpt = bookRepo.findById(bookId);
		Book managedBook = null;
		if (bookOpt.isPresent()) {
			managedBook = bookOpt.get();
			if (updatedBook.getAuthors() != null) {
			managedBook.setAuthors(updatedBook.getAuthors());
			}
			managedBook.setPrice(updatedBook.getPrice());
			managedBook.setReleaseDate(updatedBook.getReleaseDate());
			if (updatedBook.getTitle() != null && updatedBook.getTitle() != "") {
			managedBook.setTitle(updatedBook.getTitle());
			}
			bookRepo.saveAndFlush(managedBook);
		}
		return managedBook;
	}

	@Override
	public boolean delete(int bookId) {
		boolean deleted = false;
		Optional<Book> bookOpt = bookRepo.findById(bookId);
		Book bookToDelete = null;
		if (bookOpt.get() != null) {
			bookToDelete = bookOpt.get();
			bookRepo.delete(bookToDelete);
			deleted = true;
		}
		return deleted;
	}
}