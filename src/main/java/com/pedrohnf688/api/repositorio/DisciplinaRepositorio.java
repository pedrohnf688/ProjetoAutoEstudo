package com.pedrohnf688.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Integer> {

}
