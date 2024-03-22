package com.example.PruebaLinktic.dtos.request;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    @NotNull
    private int tipo_identificacion;

    @NotNull
    private int nro_identificacion;

    @NotNull
    private int valor_asegurado;

}
