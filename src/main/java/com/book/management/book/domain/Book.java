package com.book.management.book.domain;

import com.book.management.author.domain.Author;
import com.book.management.genre.domain.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date publishYear;
    @Min(1)
    private int pageCount;
    private String description;
    private String imageUrl;
    @Min(0)
    @Max(5)
    private int rating;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @NotBlank(message = "Author cannot be null ")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    @NotBlank(message = "Genre cannot be null ")
    private Genre genre;



}
