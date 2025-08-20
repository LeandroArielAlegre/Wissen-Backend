package com.api.wissenapi.repositories;

import com.api.wissenapi.models.Book.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepository  extends JpaRepository<GeneroModel, Long> {
}
