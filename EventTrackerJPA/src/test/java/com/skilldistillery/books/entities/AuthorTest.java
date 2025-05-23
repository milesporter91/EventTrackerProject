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

class AuthorTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Author author;

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
		author = em.find(Author.class, 1);	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		author = null;
	}

	@Test
	void test_Author_entity_mapping() {
		assertNotNull(author);
		assertEquals("Jay", author.getFirstName());
	}
	
	@Test
	void test_Author_Book_MTM_mapping() {
		List<Book> books = author.getBooks();
		assertTrue(books.size() > 0);
		assertEquals("Empire of the Vampire", books.get(0).getTitle());
	}

}
