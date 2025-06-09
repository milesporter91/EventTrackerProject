import { BookService } from './../../services/book-service';
import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Author } from '../../models/author';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit{

  books: Book[] = [];
  authors: Author[] = [];
  selected: Book | null = null;
  newBookForm: boolean = false;
  newBook: Book = new Book();
  editBook: Book | null = null;


  constructor(
    private bookService: BookService
  ) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.index().subscribe({
      next: (bookList) => {
        this.books = bookList;
      },
      error: (badNews) => {
        console.error('Home.loadBooks: error getting books');
        console.error(badNews);
      }
    });
  }

  displayBook(book: Book): void {
    this.selected = book;
    this.newBookForm = false;
  }

  displayTable(): void {
    this.selected = null;
    this.newBookForm = false;
  }

  displayNewBookForm(): void {
    this.selected = null;
    this.newBookForm = true;
  }

  addBook(book: Book) {
    this.bookService.create(book).subscribe({
      next: (newBook) => {
        this.loadBooks();
        this.selected = null;
        this.newBook = new Book();
        this.newBookForm = false;
      },
      error: (someError) => {
        console.error('Todo-List.ts Component: Error creating todo');
        console.error(someError);
      },
    });
  }

 getPagesReadCount(): number {
    let pagesRead = 0;
    for (let book of this.books) {
      if (book.finished === true) {
        pagesRead = pagesRead + book.numberOfPages;
      }
    }
    return pagesRead;
  }

deleteBook(bookId: number) {
    this.bookService.destroy(bookId).subscribe({
      next: () => {
        this.loadBooks();
        this.selected = null;
        this.newBookForm = false;
      },
      error: (someError) => {
        console.error('Todo-List.ts Component: Error deleting todo');
        console.error(someError);
      },
    });
  }

updateBook(updatedBook: Book) {
    this.bookService.update(updatedBook).subscribe({
      next: (updatedBook) => {
        this.loadBooks();
        this.selected = null;
        this.editBook = null;
      },
      error: (someError) => {
         console.error('Book.ts Component: Error updating book');
        console.error(someError);
      }
    });
  }

  showUpdateForm(updatedBook: Book): void {
    this.selected = null;
    this.newBookForm = false;
    this.editBook = Object.assign({}, updatedBook);
  }

}
