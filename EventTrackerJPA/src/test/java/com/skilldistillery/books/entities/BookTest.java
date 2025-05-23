package com.skilldistillery.books.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class BookTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Book book;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		book = em.find(Book.class, 1);	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		book = null;
	}

	@Test
	void test_Book_entity_mapping() {
		assertNotNull(book);
		assertEquals("Empire of the Vampire", book.getTitle());
	}
	
	@Test
	void test_Book_Author_MTM_mapping() {
		List<Author> authors = book.getAuthors();
		assertTrue(authors.size() > 0);
		assertEquals("Jay", authors.get(0).getFirstName());
	}

}
