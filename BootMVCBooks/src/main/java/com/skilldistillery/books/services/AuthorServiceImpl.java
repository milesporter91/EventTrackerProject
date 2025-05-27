package com.skilldistillery.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.books.entities.Author;
import com.skilldistillery.books.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepo;

	@Override
	public Author findById(int id) {
		return authorRepo.findById(id).orElse(null);
	}

	@Override
	public List<Author> findAll() {
		return authorRepo.findAll();
	}

	@Override
	public Author create(Author newAuthor) {
		authorRepo.saveAndFlush(newAuthor);
		return newAuthor;
	}

	@Override
	public Author update(int authorId, Author updatedAuthor) {
		Optional<Author> authorOpt = authorRepo.findById(authorId);
		Author managedAuthor = null;
		if (authorOpt.isPresent()) {
			managedAuthor = authorOpt.get();
			if (managedAuthor.getBooks() != null) {
				managedAuthor.setBooks(updatedAuthor.getBooks());
			}
			if (updatedAuthor.getFirstName() != null && updatedAuthor.getFirstName() != "") {
			managedAuthor.setFirstName(updatedAuthor.getFirstName());
			}
			if (updatedAuthor.getLastName() != null && updatedAuthor.getLastName() != "") {
			managedAuthor.setLastName(updatedAuthor.getLastName());
			}
			authorRepo.saveAndFlush(managedAuthor);
		}
		return managedAuthor;
	}
	

	@Override
	public boolean delete(int authorId) {
		boolean deleted = false;
		Optional<Author> authorOpt = authorRepo.findById(authorId);
		Author authorToDelete = null;
		if (authorOpt.get() != null) {
			authorToDelete = authorOpt.get();
			authorRepo.delete(authorToDelete);
			deleted = true;
		}
		return deleted;
	}

}
