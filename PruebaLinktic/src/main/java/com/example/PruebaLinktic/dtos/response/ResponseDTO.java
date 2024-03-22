package com.example.PruebaLinktic.dtos.response;

import com.example.PruebaLinktic.dtos.AmparoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private int tipo_identificacion;
    private int numero_identificacion;
    private int valor_asegurado;
    private List<AmparoDTO> liquidacion;
    private double valor_total;

}
