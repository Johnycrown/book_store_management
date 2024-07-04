package com.book.management.genre.service;

import com.book.management.exception.ResourcesNotFoundException;
import com.book.management.genre.domain.Genre;
import com.book.management.genre.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres( int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));

        return genreRepository.findAll(pageable).stream().toList();
    }

    public Genre getGenreById(Long id) throws ResourcesNotFoundException {
        return genreRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Genre not found"));
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre updateGenre(Long id, Genre genreDetails) throws ResourcesNotFoundException {
        Genre genre = getGenreById(id);
        genre.setName(genreDetails.getName());
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) throws ResourcesNotFoundException {
        Genre genre = getGenreById(id);
        genreRepository.delete(genre);
    }
}
