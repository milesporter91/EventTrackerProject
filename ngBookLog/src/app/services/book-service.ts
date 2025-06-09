import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Book } from '../models/book';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private url = environment.baseUrl + 'api/books';


  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
  ) { }

  index(): Observable<Book[]> {
    return this.http.get<Book[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error retrieving books: ' + err)
        );
      })
    );
  }

   create(book: Book): Observable<Book> {
    return this.http.post<Book>(this.url, book).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error creating todo: ' + err)
        );
      })
    );
  }

destroy(bookId: number): Observable<void> {
    return this.http.delete<void>(this.url + "/" + bookId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error deleting book: ' + err)
        );
      })
    );
  }

update(updatedBook: Book): Observable<Book> {
    if (updatedBook.finished) {
      updatedBook.dateFinished = this.datePipe.transform(Date.now(), 'shortDate') ?? '';
    } else {
      updatedBook.dateFinished = '';
    }

    return this.http.put<Book>(this.url + "/" + updatedBook.id, updatedBook).pipe(
       catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BookService.index(): error updating book: ' + err)
        );
      })
    )
  }

}
