package com.example.PruebaLinktic.repository;

import com.example.PruebaLinktic.entity.Amparo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmparoRepository extends JpaRepository<Amparo, Long> {
}
