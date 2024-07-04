# Bookstore RESTful Web Service

## Overview
This is a RESTful web service for managing a bookstore, allowing CRUD operations for books, authors, and genres.

## Technologies
- Spring Boot
- Spring Data JPA
- MySQL

## Setup Instructions
1. Clone the repository.
2. Update the database configuration in `src/main/resources/application.properties`.
3. Run the application using your IDE or `mvn spring-boot:run`.

## API Endpoints

### Authors
- `GET /api/authors` - Retrieve all authors
- `GET /api/authors/{id}` - Retrieve an author by ID
- `POST /api/authors` - Create a new author
- `PUT /api/authors/{id}` - Update an existing author
- `DELETE /api/authors/{id}` - Delete an author

### Genres
- `GET /api/genres` - Retrieve all genres
- `GET /api/genres/{id}` - Retrieve a genre by ID
- `POST /api/genres` - Create a new genre
- `PUT /api/genres/{id}` - Update an existing genre
- `DELETE /api/genres/{id}` - Delete a genre

### Books
- `GET /api/books` - Retrieve all books
- `GET /api/books/{id}` - Retrieve a book by ID
- `POST /api/books` - Create a new book
- `PUT /api/books/{id}` - Update an existing book
- `DELETE /api/books/{id}` - Delete a book
