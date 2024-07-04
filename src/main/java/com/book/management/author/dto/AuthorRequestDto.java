package com.book.management.author.dto;

import com.book.management.book.domain.Book;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AuthorRequestDto {
    private Long id;
    private String name;
    private List<String> awards;
    private Book book;
}
