package com.pedrohnf688.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Disciplina;
import com.pedrohnf688.api.modelo.Estudante;

@Repository
public interface EstudanteRepositorio extends JpaRepository<Estudante, Integer>{

	
}
