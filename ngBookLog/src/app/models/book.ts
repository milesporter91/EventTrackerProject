import { Author } from './author';
export class Book {
  id: number;
  title: String;
  authors: Author[];
  releaseDate: string | null;
  price: number;
  dateStarted: string | null;
  dateFinished: string | null;
  numberOfPages: number;
  finished: boolean;

  constructor(
  id: number = 0,
  title: String = '',
  authors: Author[] = [],
  releaseDate: string | null = null,
  price: number = 0,
  dateStarted: string | null = null,
  dateFinished: string | null = null,
  numberOfPages: number = 0,
  finished: boolean = false,
  ) {
    this.id = id;
    this.title = title;
    this.authors = authors;
    this.releaseDate = releaseDate;
    this.price = price;
    this.dateStarted = dateStarted;
    this.dateFinished = dateFinished;
    this.numberOfPages = numberOfPages;
    this.finished = finished;
  }

}
