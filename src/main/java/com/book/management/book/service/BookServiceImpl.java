package com.book.management.book.service;

import com.book.management.author.domain.Author;
import com.book.management.author.repository.AuthorRepository;
import com.book.management.book.domain.Book;
import com.book.management.book.dto.BookRequestDto;
import com.book.management.book.repository.BookRepository;
import com.book.management.exception.ResourcesNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public Book createBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        try {


            log.info("create book request : {}", bookRequestDto);
            Author author = new Author();
            if (bookRequestDto.getAuthor().getId() > 0) {
                author = authorRepository.findById(bookRequestDto.getAuthor().getId()).orElseThrow(() -> new ResourcesNotFoundException("Author with the id " + bookRequestDto.getAuthor().getId() + "  not found"));

            }
            author = bookRequestDto.getAuthor();

            book = modelMapper.map(bookRequestDto, Book.class);
            book.setAuthor(author);
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


        return bookRepository.findByAuthor(id);
    }

    @Override
    public Book updateBook(Long bookId, BookRequestDto bookRequestDto) throws IllegalAccessException, ResourcesNotFoundException {
        log.info("update book request {}", bookRequestDto);
        if (bookId <= 0) {
            throw new IllegalAccessException("the id provided is invalid");
        }
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourcesNotFoundException("No record found with the book id provided"));
        modelMapper.map(bookRequestDto, book);
        Book updatedBook = bookRepository.save(book);
        log.info("book updated successfully {}", updatedBook);
        return updatedBook;
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
