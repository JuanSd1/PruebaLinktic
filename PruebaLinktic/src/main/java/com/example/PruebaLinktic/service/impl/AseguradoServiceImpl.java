package com.example.PruebaLinktic.service.impl;

import com.example.PruebaLinktic.dtos.AmparoDTO;
import com.example.PruebaLinktic.dtos.request.RequestDTO;
import com.example.PruebaLinktic.dtos.response.ResponseDTO;
import com.example.PruebaLinktic.entity.*;
import com.example.PruebaLinktic.repository.AmparoRepository;
import com.example.PruebaLinktic.repository.AseguradoRepository;
import com.example.PruebaLinktic.repository.LiquidacionRepository;
import com.example.PruebaLinktic.service.AseguradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AseguradoServiceImpl implements AseguradoService {

    @Autowired
    private AseguradoRepository aseguradoRepository;

    @Override
    public void guardarAsegurado(Asegurado asegurado) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(asegurado.getFechaNacimiento(), formatter);
        asegurado.setFechaNacimiento(fechaNacimiento.format(formatter));
        aseguradoRepository.save(asegurado);
    }

    @Override
    public Asegurado obtenerAsegurado(Long id) {
        return aseguradoRepository.findById(id).orElse(null);
    }

    @Override
    public Asegurado actualizarAsegurado(Long id, Asegurado aseguradoActualizado) {
        Asegurado aseguradoExistente = aseguradoRepository.findById(id).orElse(null);
        if (aseguradoExistente != null){

            aseguradoExistente.setTipoIdentificacion(aseguradoActualizado.getTipoIdentificacion());
            aseguradoExistente.setNumeroIdentificacion(aseguradoActualizado.getNumeroIdentificacion());
            aseguradoExistente.setNombre(aseguradoActualizado.getNombre());
            aseguradoExistente.setApellido(aseguradoActualizado.getApellido());
            aseguradoExistente.setGenero(aseguradoActualizado.getGenero());
            aseguradoExistente.setFechaNacimiento(aseguradoActualizado.getFechaNacimiento());
            aseguradoExistente.setValorAsegurado(aseguradoActualizado.getValorAsegurado());

            return aseguradoRepository.save(aseguradoExistente);
        }else {
            return null;
        }
    }

    @Override
    public void eliminarAsegurado(Long id) {
        aseguradoRepository.deleteById(id);
    }

    @Override
    public ResponseDTO calcularLiquidacion(RequestDTO requestDTO) {

        int numeroIdentificacion = requestDTO.getNro_identificacion();
        Asegurado asegurado = aseguradoRepository.findByNumeroIdentificacion(numeroIdentificacion);
        if (asegurado == null) {
            throw new IllegalArgumentException("No se encontró ningún asegurado con el número de identificación proporcionado.");
        }

        List<Liquidacion> liquidaciones = asegurado.getLiquidacion();
        if (liquidaciones.isEmpty()) {
            throw new IllegalArgumentException("No hay liquidaciones asociadas a este asegurado.");
        }

        List<AmparoDTO> liquidacion = new ArrayList<>();
        double valorTotal = 0.0;

        for (Liquidacion liquidacions : liquidaciones) {
            Prima prima = liquidacions.getPrima();
            if (prima != null) {
                int edadAsegurado = calcularEdad(asegurado.getFechaNacimiento());
                if (edadAsegurado >= prima.getEdadMinima() && edadAsegurado <= prima.getEdadMaxima()) {
                    Amparo amparo = prima.getAmparo();
                    if (amparo != null) {
                        double porcentajePrima = prima.getPorcentajePrima();
                        double valorPrima = calcularValorPrima(asegurado.getValorAsegurado(), porcentajePrima);
                        liquidacion.add(new AmparoDTO(amparo.getCodigo(), amparo.getNombre(), prima.getPorcentajePrima()));
                        valorTotal += valorPrima;
                    }
                }
            }
        }

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setTipo_identificacion(requestDTO.getTipo_identificacion());
        responseDTO.setNumero_identificacion(requestDTO.getNro_identificacion());
        responseDTO.setValor_asegurado(requestDTO.getValor_asegurado());
        responseDTO.setLiquidacion(liquidacion);
        responseDTO.setValor_total(valorTotal);

        return responseDTO;

    }

    private int calcularEdad(String fechaNacimiento){
        LocalDate fechaNacimientos = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNacimientos, ahora);
        return periodo.getYears();
    }

    private double calcularValorPrima(int valorAsegurado, double procentajePrima){
        return  valorAsegurado * procentajePrima;
    }

}
