package com.book.management.book.service;

import com.book.management.author.domain.Author;
import com.book.management.author.repository.AuthorRepository;
import com.book.management.book.domain.Book;
import com.book.management.book.dto.BookRequestDto;
import com.book.management.book.repository.BookRepository;
import com.book.management.exception.ResourcesNotFoundException;
import com.book.management.genre.domain.Genre;
import com.book.management.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;


    @Override
    @Transactional
    public Book createBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        try {


            log.info("create book request : {}", bookRequestDto);
            Author author = new Author();
            if (bookRequestDto.getAuthor().getId()!=null && bookRequestDto.getAuthor().getId()>0 ) {
                author = authorRepository.findById(bookRequestDto.getAuthor().getId()).orElseThrow(() -> new ResourcesNotFoundException("Author with the id " + bookRequestDto.getAuthor().getId() + "  not found"));
            }
            else author = authorRepository.save(bookRequestDto.getAuthor());

            Genre genre = new Genre();
            if (bookRequestDto.getGenre().getId() !=null && bookRequestDto.getGenre().getId() > 0) {
                genre = genreRepository.findById(bookRequestDto.getGenre().getId())
                        .orElseThrow(() -> new ResourcesNotFoundException("Genre not found"));

            }

            else genre = genreRepository.save(bookRequestDto.getGenre());
            book = modelMapper.map(bookRequestDto, Book.class);


            book.setAuthor(author);
            book.setGenre(genre);
            Book savedBook = bookRepository.save(book);
            log.info("book record saved succesfully {}", savedBook);
            book = savedBook;


        } catch (Exception e) {
            log.error("exception caught while creating book entity {}", e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> fetchBookByauthor(Long id) throws IllegalAccessException {
        if (id <= 0) {
            throw new IllegalAccessException("the id provided is invalid");
        }


        return bookRepository.findByAuthorId(id);
    }

    @Override
    @Transactional
    public Book updateBook(Long bookId, BookRequestDto bookRequestDto) throws IllegalAccessException, ResourcesNotFoundException {
        log.info("Update book request: {} for book ID: {}", bookRequestDto, bookId);

        if (bookId == null || bookId <= 0) {
            throw new IllegalAccessException("The ID provided is invalid: " + bookId);
        }

        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourcesNotFoundException("No record found with the book ID provided: " + bookId));

        try {
            modelMapper.map(bookRequestDto, existingBook);

            if (bookRequestDto.getAuthor().getId() != null) {
                Author author = authorRepository.findById(bookRequestDto.getAuthor().getId())
                        .orElseThrow(() -> new ResourcesNotFoundException("Author with ID " + bookRequestDto.getAuthor().getId() + " not found"));
                existingBook.setAuthor(author);
            }

            if (bookRequestDto.getGenre().getId() != null) {
                Genre genre = genreRepository.findById(bookRequestDto.getGenre().getId())
                        .orElseThrow(() -> new ResourcesNotFoundException("Genre with ID " + bookRequestDto.getGenre().getId() + " not found"));
                existingBook.setGenre(genre);
            }

            Book updatedBook = bookRepository.save(existingBook);
            log.info("Book updated successfully: {}", updatedBook);
            return updatedBook;
        } catch (Exception e) {
            log.error("Exception caught while updating book entity: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update book", e);
        }
    }

    @Override
    public List<Book> fetchBookByTitle(String title) throws IllegalAccessException {
        if (title.isBlank()) {
            throw new IllegalAccessException("the book title required");
        }
        return bookRepository.findByTitle(title);
    }

    @Override
    public String deleteBook(Long id) throws ResourcesNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Book with id " + id + " not found"));
        bookRepository.delete(book);
        return "book has been successfully deleted";
    }

    @Override
    public Book fetchBookById(Long id) throws ResourcesNotFoundException, IllegalAccessException {
        if (id <= 0) {
            throw new IllegalAccessException("the id provided is invalid");
        }
        return bookRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public List<Book> fetchAllBook(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return bookRepository.findAll(pageable).stream().toList();
    }
}
