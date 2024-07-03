package com.book.management.author.service;

import com.book.management.author.domain.Author;
import com.book.management.author.dto.AuthorRequestDto;
import com.book.management.exception.ResourcesNotFoundException;

import java.util.List;

public interface AuthorService {


    public List<Author> getAllAuthors();
    public Author getAuthorById(Long id) throws ResourcesNotFoundException;
    public Author createAuthor(AuthorRequestDto author);
    public Author updateAuthor(Long id, AuthorRequestDto authorDetails) throws ResourcesNotFoundException, IllegalAccessException;
    public void deleteAuthor(Long id) throws ResourcesNotFoundException;

}
