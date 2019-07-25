package com.ponto.novo.pontonovo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ponto.novo.pontonovo.entities.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Integer> {

}
