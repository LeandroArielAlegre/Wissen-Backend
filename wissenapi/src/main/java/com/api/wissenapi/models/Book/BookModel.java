package com.api.wissenapi.models.Book;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="libro")
public class BookModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String autor;

    @Column (name ="enlaceArchivo")
    private String enlaceArchivo;

    @Column
    private String portada;

    @ManyToMany
    @JoinTable(
            name = "libro_tags",
            joinColumns = @JoinColumn(name = "id_libro"),
            inverseJoinColumns = @JoinColumn(name = "idlibro_genero")
    )
    @JsonManagedReference
    private List<GeneroModel> generos;
}
