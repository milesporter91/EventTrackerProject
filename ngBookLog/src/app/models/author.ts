import { Book } from "./book";

export class Author {
  id: number;
  firstName: String;
  lastName: String;
  books: Book[];

  constructor(
     id: number = 0,
  firstName: String = '',
  lastName: String = '',
  books: Book[] = [],
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.books = books;
  }
}
