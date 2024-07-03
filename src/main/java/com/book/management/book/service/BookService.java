package com.book.management.book.service;

import com.book.management.book.domain.Book;
import com.book.management.book.dto.BookRequestDto;
import com.book.management.exception.ResourcesNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book createBook(BookRequestDto bookRequestDto);
    List<Book> fetchBookByauthor(Long id) throws IllegalAccessException;
    Book updateBook(Long bookId, BookRequestDto bookRequestDto) throws IllegalAccessException, ResourcesNotFoundException;
    List<Book> fetchBookByTitle(String title) throws IllegalAccessException;
    String  deleteBook(Long Id) throws ResourcesNotFoundException;
    Book fetchBookById(Long id) throws ResourcesNotFoundException, IllegalAccessException;
    List<Book> fetchAllBook(int pageNumber, int pageSize);
}
