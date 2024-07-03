package com.book.management.author.service;

import com.book.management.author.domain.Author;
import com.book.management.author.dto.AuthorRequestDto;
import com.book.management.author.repository.AuthorRepository;
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
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Author> getAllAuthors(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));

        return authorRepository.findAll(pageable).stream().toList();
    }
    @Override
    public Author getAuthorById(Long id) throws ResourcesNotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Author not found"));
    }
    @Override
    public Author createAuthor(AuthorRequestDto authorRequestDto) {
        log.info("create Author request : {}", authorRequestDto);

        Author author = modelMapper.map(authorRequestDto, Author.class);
        return authorRepository.save(author);
    }
    @Override
    @Transactional
    public Author updateAuthor(Long id, AuthorRequestDto authorDetails) throws ResourcesNotFoundException, IllegalAccessException {
        log.info("update Author request : {}", authorDetails);
        if (id <= 0) {
            throw new IllegalAccessException("the id provided is invalid");
        }
        Author author = getAuthorById(id);
        modelMapper.map(authorDetails,author);
        Author updatedAuthor = authorRepository.save(author);
        log.info("Author updated successfully : {}", updatedAuthor);

        return updatedAuthor;

    }
    @Override
    public void deleteAuthor(Long id) throws ResourcesNotFoundException {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

}
