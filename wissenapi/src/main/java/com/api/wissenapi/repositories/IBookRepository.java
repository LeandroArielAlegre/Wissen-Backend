package com.api.wissenapi.repositories;

import com.api.wissenapi.models.Book.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository  extends JpaRepository<BookModel, Long> {
}
