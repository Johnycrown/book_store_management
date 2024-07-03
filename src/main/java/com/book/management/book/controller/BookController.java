package com.book.management.book.controller;

import com.book.management.book.domain.Book;
import com.book.management.book.dto.BookRequestDto;
import com.book.management.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor

public class BookController {

    private final BookService bookService;


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber
            , @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Book> books = new ArrayList<>();
        try {
            books = bookService.fetchAllBook(pageNumber, pageSize);
        } catch (Exception e) {
            log.error("error occurred  while fetching all books {}", e.getMessage());
        }
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = new Book();
        try {
            book = bookService.fetchBookById(id);
        } catch (Exception e) {
            log.error("error occurred  while fetching a books {}", e.getMessage());
        }
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDto bookRequestDto) {
        Book book = new Book();
        try {
            book = bookService.createBook(bookRequestDto);
        } catch (Exception e) {
            log.error("error occurred  while creating a books {}", e.getMessage());

        }

        return ResponseEntity.ok().body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookDetails) {
        Book updatedBook = new Book();


        try {
            updatedBook = bookService.updateBook(id, bookDetails);
        } catch (Exception e) {
            log.error("error occurred  while updating a books {}", e.getMessage());

        }
        return ResponseEntity.ok().body(updatedBook);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {

        String response = "";
        try {
            response = bookService.deleteBook(id);

        } catch (Exception e) {
            log.error("error occurred  while creating a books {}", e.getMessage());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("author/{id}")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable long id) {
        List<Book> books = new ArrayList<>();
        try {
            books = bookService.fetchBookByauthor(id);
        } catch (Exception e) {
            log.error("error occurred  while fetching all books belong to an author {}", e.getMessage());
        }
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable String title) {
        List<Book> books = new ArrayList<>();
        try {
            books = bookService.fetchBookByTitle(title);
        } catch (Exception e) {
            log.error("error occurred  while fetching all books by title {}", e.getMessage());
        }
        return ResponseEntity.ok().body(books);
    }


}
