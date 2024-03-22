package com.example.PruebaLinktic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prima")
public class Prima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_prima",nullable = false)
    private int codigo;

    @Column(name = "edad_minima",nullable = false)
    private int edadMinima;

    @Column(name = "edad_maxima", nullable = false)
    private int edadMaxima;

    @Column(name = "porcentaje_prima",nullable = false)
    private double porcentajePrima;

    @ManyToOne
    @JoinColumn(name = "amparo_id")
    private Amparo amparo;
}
