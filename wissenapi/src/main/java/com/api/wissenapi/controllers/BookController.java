package com.api.wissenapi.controllers;


import com.api.wissenapi.models.Book.BookModel;
import com.api.wissenapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping(path = "/postBook")
    public ResponseEntity<?> saveBook(@RequestBody Map<String, Object>  request) {

        try{
            BookModel book = new BookModel();
            book.setNombre((String) request.get("nombre"));
            book.setDescripcion((String) request.get("descripcion"));
            book.setAutor((String) request.get("autor"));
            book.setEnlaceArchivo((String) request.get("enlaceArchivo"));
            book.setPortada((String) request.get("portada"));
            @SuppressWarnings("unchecked")
            List<Long> genreIds = (List<Long>) request.get("generos");
            BookModel savedBook = bookService.saveBook(book, genreIds);
            return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping (path = "/getAllBooks")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> books = bookService.findAll();
        if(books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(books,HttpStatus.OK);
        }
    }

}
