package com.example.PruebaLinktic.service;

import com.example.PruebaLinktic.dtos.request.RequestDTO;
import com.example.PruebaLinktic.dtos.response.ResponseDTO;
import com.example.PruebaLinktic.entity.Asegurado;

public interface AseguradoService {

    void guardarAsegurado(Asegurado asegurado);
    Asegurado obtenerAsegurado(Long id);
    Asegurado actualizarAsegurado(Long id, Asegurado aseguradoActualizado);
    void eliminarAsegurado(Long id);
    ResponseDTO calcularLiquidacion(RequestDTO requestDTO);

}
