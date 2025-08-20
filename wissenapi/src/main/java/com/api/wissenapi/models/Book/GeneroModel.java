package com.api.wissenapi.models.Book;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "libro_genero")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro_genero")
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "generos")
    @JsonBackReference
    private List<BookModel> libros;
}
