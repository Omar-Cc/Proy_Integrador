package com.utp.integradorspringboot.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "historiales_clinico")
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor  //constructor vacio
@Data               //getters and setters
public class HistorialClinico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Column(name = "fechayhora_consulta", nullable = false)
    private Date fechayhoraConsulta;

    private String descripcionConsulta;
    private String diagnostico;
    private String tratamiento;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;
}