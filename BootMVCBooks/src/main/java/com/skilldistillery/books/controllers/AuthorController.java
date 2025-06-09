package com.skilldistillery.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.books.entities.Author;
import com.skilldistillery.books.services.AuthorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping({ "authors", "authors/" })
	public List<Author> index() {
		return authorService.findAll();
	}
	
	@GetMapping("authors/{authorId}")
	public Author findById(@PathVariable("authorId") int authorId, HttpServletResponse res) {
		Author author = authorService.findById(authorId);
		if (author == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return author;
	}
	
	@PostMapping({ "authors", "authors/" })
	public Author putForBook(@RequestBody Author newAuthor, HttpServletResponse res, HttpServletRequest req) {
		try {
			newAuthor = authorService.create(newAuthor);
			if (newAuthor != null) {
				res.setStatus(HttpServletResponse.SC_CREATED);
				String url = req.getRequestURL().append("/").append(newAuthor.getId()).toString();
				res.setHeader("location", url);
			}
			else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			newAuthor = null;
		}
		return newAuthor;
	}
	
	@PutMapping("authors/{authorId}")
	public Author updateAuthor(@PathVariable("authorId") int authorId, @RequestBody Author author, HttpServletResponse res) {

		try {
			author = authorService.update(authorId, author);
			if (author == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return author;
	}
	
	@DeleteMapping("authors/{authorId}")
	public void deleteFilm(@PathVariable("authorId") int authorId, HttpServletResponse res) {
		try {
			if (authorService.delete(authorId)) {
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
