package com.example.PruebaLinktic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asegurado")
public class Asegurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id")
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "numero_identificacion", unique = true, nullable = false)
    private int numeroIdentificacion;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellido",nullable = false)
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @Column(name = "fecha_nacimiento",nullable = false)
    private String fechaNacimiento;

    @Column(name = "valor_asegurado")
    private int valorAsegurado;

    @OneToMany(mappedBy = "asegurado")
    private List<Liquidacion> liquidacion;

}
