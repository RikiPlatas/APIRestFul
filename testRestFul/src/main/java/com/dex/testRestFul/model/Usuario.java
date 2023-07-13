package com.dex.testRestFul.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "edad")
    private int edad;
}
