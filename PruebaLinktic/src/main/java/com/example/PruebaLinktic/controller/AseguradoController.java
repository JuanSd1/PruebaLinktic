package com.example.PruebaLinktic.controller;

import com.example.PruebaLinktic.dtos.request.RequestDTO;
import com.example.PruebaLinktic.dtos.response.ResponseDTO;
import com.example.PruebaLinktic.entity.Asegurado;
import com.example.PruebaLinktic.service.AseguradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asegurado")
public class AseguradoController {

    @Autowired
    private AseguradoService aseguradoService;

    @PostMapping("/guardar")
    public void guardarAsegurado(@RequestBody Asegurado asegurado){
        aseguradoService.guardarAsegurado(asegurado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asegurado> obtenerAsegurado(@PathVariable Long id) {
        Asegurado asegurado = aseguradoService.obtenerAsegurado(id);
        if (asegurado != null) {
            return ResponseEntity.ok(asegurado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Asegurado> actualizarAsegurado(@PathVariable Long id, @RequestBody Asegurado asegurado) {
        Asegurado aseguradoActualizado = aseguradoService.actualizarAsegurado(id, asegurado);
        if (aseguradoActualizado != null) {
            return ResponseEntity.ok(aseguradoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarAsegurado(@PathVariable Long id) {
        aseguradoService.eliminarAsegurado(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calcularLiquidacion")
    public ResponseEntity<ResponseDTO> calcularLiquidacion(@RequestBody RequestDTO requestDTO){
        try {
            ResponseDTO responseDTO = aseguradoService.calcularLiquidacion(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
