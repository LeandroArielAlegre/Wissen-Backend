package com.api.wissenapi.services;

import com.api.wissenapi.models.Book.BookModel;
import com.api.wissenapi.models.Book.GeneroModel;
import com.api.wissenapi.repositories.IBookRepository;
import com.api.wissenapi.repositories.IGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IGeneroRepository generoRepository;
    public BookModel saveBook (BookModel book, List<Long> genreIds) {
        try{
            if (genreIds != null && !genreIds.isEmpty()) {
                List<GeneroModel> generos = generoRepository.findAllById(genreIds);
                book.setGeneros(generos);
            }
            BookModel savedBook = bookRepository.save(book);
            return savedBook;

        }catch (Exception e){
            throw new RuntimeException("No se pudo guardar el libro", e);
        }
    }
    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }
    public BookModel findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


}
