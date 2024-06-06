package com.cartelera.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartelera.entidades.Sala;
@Repository
public interface SalaRepositorio extends JpaRepository<Sala,Long>{

}
