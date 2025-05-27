# EventTrackerProject

## Description
- Book Event Tracker is a REST application that allows a user to log books they have read. The application uses a relational database and provides basic CRUD functionality.

## Technologies used
- Java
- Spring MVC
- Spring JPA
- MAMP
- MySQL
- MySQL Workbench
- Gradle
- Junit

## Concepts applied
- Object-Oriented Programming(OOP)
- Object Relational Mapping(ORM)
- Database CRUD functionality

## Lessons learned
 - Mapping pathvariables to make CRUD functions work
 - Managing parent-child relationships while performing CRUD operations


### Endpoints

| HTTP Verb | URI                  | Request Body              | Response Body              | Status               |
|-----------|----------------------|---------------------------|----------------------------|----------------------|
| GET       | /api/books       |                           | List all books          | 200                  |
| GET       | /api/books/1    |                           | Book by ID           | 200 or 404           |
| POST      | /api/books       | JSON of new book     | JSON of created book   | 201 or 400           |
| PUT       | /api/books/1    | JSON for updating book| JSON of updated book   | 200, 404, or 400     |
| DELETE    | /api/books/17    |                           |                            | 204, 404, or 400     |
| GET       | /api/authors       |                           | List all authors          | 200                  |
| GET       | /api/authors/1    |                           | Author by ID           | 200 or 404           |
| POST      | /api/authors       | JSON of new author     | JSON of created author   | 201 or 400           |
| PUT       | /api/authors/1    | JSON for updating author| JSON of updated author   | 200, 404, or 400     |
| DELETE    | /api/authors/17    |                           |                            | 204, 404, or 400     |

