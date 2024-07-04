package com.book.management.author.controller;

import com.book.management.author.domain.Author;
import com.book.management.author.dto.AuthorRequestDto;
import com.book.management.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAllAuthors(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber
            , @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        try {
            return ResponseEntity.ok().body(authorService.getAllAuthors(pageNumber, pageSize));
        }
        catch (Exception e) {
            log.error("error occurred  while fetching all  author {}", e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        try {
            Author author = authorService.getAuthorById(id);
            return ResponseEntity.ok().body(author);
        }
        catch (Exception e) {
            log.error("error occurred  while fetching an  author {}", e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorRequestDto author) {
        try {
            return ResponseEntity.ok(authorService.createAuthor(author));
        }
        catch (Exception e) {
            log.error("error occurred  while creating an  author {}", e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDto authorDetails) {
        try {
            Author updatedAuthor = authorService.updateAuthor(id, authorDetails);

            return ResponseEntity.ok().body(updatedAuthor);
        }
        catch (Exception e) {
            log.error("error occurred  while updating an  author {}", e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {


            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error("error occurred  while deleting an  author {}", e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }
}
