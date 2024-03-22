package com.example.PruebaLinktic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "genero")
public class Genero {

    @Id
    private Long id;

    @Column(name = "codigo")
    private int codigo;

    @Column(name = "descripcion")
    private String descripcion;

}
