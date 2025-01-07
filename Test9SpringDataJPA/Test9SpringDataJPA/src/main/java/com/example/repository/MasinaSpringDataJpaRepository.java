package com.example.repository;

import com.example.entity.Masina;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface MasinaSpringDataJpaRepository extends JpaRepository<Masina, String> {

    List<Masina> findByNrInmatriculare(String nrInmatriculare);

    List<Masina> findByAnFabricatieGreaterThan(int anFabricatie);

    int countByNrKilometriiLessThan(int nrKilometrii);

    int countByMarca(String marca);
    @Transactional
    @Modifying
    void deleteByNrInmatriculare(String nrInmatriculare);
}