package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "categorias")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_categoria", nullable = false, length = 30)
    private String nombreCategoria;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoriaPadre")
    private Categoria categoriaPadre;
}