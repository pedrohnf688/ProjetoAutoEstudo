package com.pedrohnf688.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Estudante;

@Transactional(readOnly = true)
@Repository
public interface EstudanteRepositorio extends JpaRepository<Estudante, Integer> {

}
