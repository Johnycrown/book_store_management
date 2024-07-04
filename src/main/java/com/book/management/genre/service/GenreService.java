package com.book.management.genre.service;

import com.book.management.exception.ResourcesNotFoundException;
import com.book.management.genre.domain.Genre;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GenreService {


    List<Genre> getAllGenres( int pageNumber, int pageSize);

    Genre getGenreById(Long id) throws ResourcesNotFoundException;

    public Genre createGenre(Genre genre);

    Genre updateGenre(Long id, Genre genreDetails) throws ResourcesNotFoundException;


    public void deleteGenre(Long id) throws ResourcesNotFoundException;
}
