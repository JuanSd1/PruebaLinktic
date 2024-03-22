package com.example.PruebaLinktic.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmparoDTO {

    private int codigo_amparo;
    private String nombre_amparo;
    private double valor_prima;

}
