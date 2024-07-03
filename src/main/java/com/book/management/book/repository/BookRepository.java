package com.book.management.book.repository;

import com.book.management.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book>  findByAuthor(Long authorId);
    List<Book>  findByTitle(String bookName);

}
