package com.example.PruebaLinktic.repository;

import com.example.PruebaLinktic.entity.Asegurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AseguradoRepository extends JpaRepository<Asegurado, Long> {

    Asegurado findByNumeroIdentificacion(int numeroIdentificacion);

}
