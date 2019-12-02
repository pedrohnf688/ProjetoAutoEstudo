package com.pedrohnf688.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pedrohnf688.api.modelo.Estudante;
import com.pedrohnf688.api.modelo.enums.EnumSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoEscolaridade;

@SpringBootApplication
public class ProjetoAutoEstudoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAutoEstudoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//
//			Estudante e = new Estudante();
//			e.setInstituicao("fsdfsd");
//			e.setDataNascimento("01/02/1323");
//			e.setDescricao("ffefrere");
//			e.setEscolaridade(EnumTipoEscolaridade.ENSINO_MEDIO);
//			e.setNome("pedro");
//			e.setSexo(EnumSexo.MASCULINO);
//		};
//	}
}
