package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "pacientes")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class Paciente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String direccion;

    @Column(length = 9)
    private String telefono;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_ubigeo", nullable = false)
    private Ubigeo ubigeo;
}

enum Genero {
    Mujer,
    Hombre
}