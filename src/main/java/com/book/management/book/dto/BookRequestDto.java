package com.book.management.book.dto;

import com.book.management.author.domain.Author;
import com.book.management.genre.domain.Genre;
import lombok.Data;

import java.util.Date;
import java.util.Set;
@Data
public class BookRequestDto {
    private String title;
    private Author author;
    private Date publishYear;
    private int pageCount;
    private String description;
    private String imageUrl;
    private int rating;
    private Set<Genre>  genre;
}
