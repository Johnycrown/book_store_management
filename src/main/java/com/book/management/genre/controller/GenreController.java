package com.book.management.genre.controller;

import com.book.management.genre.domain.Genre;
import com.book.management.genre.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
@Slf4j
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;


    @GetMapping
    public List<Genre> getAllGenres(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber
            , @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return genreService.getAllGenres(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        try {
            Genre genre = genreService.getGenreById(id);
            return ResponseEntity.ok().body(genre);
        }
        catch (Exception e){
            log.info("exception caught while fetching genre");
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createGenre( @Valid @RequestBody Genre genre) {
        try {
            return ResponseEntity.ok().body(genreService.createGenre(genre));
        }catch (Exception e){
            log.info("exception caught while creating genre");
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable Long id, @Valid @RequestBody Genre genreDetails) {
       try {
           Genre updatedGenre = genreService.updateGenre(id, genreDetails);
           return ResponseEntity.ok().body(updatedGenre);
       }
       catch (Exception e){
           log.info("exception caught while updating genre");
           return ResponseEntity.noContent().build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        try {
            genreService.deleteGenre(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            log.info("exception caught while deleting genre");
            return ResponseEntity.noContent().build();
        }
    }
}
