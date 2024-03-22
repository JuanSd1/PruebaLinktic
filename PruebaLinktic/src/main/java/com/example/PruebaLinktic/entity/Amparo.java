package com.example.PruebaLinktic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "amparo")
public class Amparo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_amparos", unique = true, nullable = false)
    private int codigo;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "amparo")
    private List<Prima> prima;

}
