package com.pedrohnf688.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.Disciplina;
import com.pedrohnf688.api.modelo.Estudante;
import com.pedrohnf688.api.modelo.enums.EnumSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoEscolaridade;
import com.pedrohnf688.api.servico.CredencialServico;
import com.pedrohnf688.api.servico.DisciplinaServico;
import com.pedrohnf688.api.servico.EstudanteServico;

@SpringBootApplication
public class ProjetoAutoEstudoApplication {

	@Autowired
	private EstudanteServico es;

	@Autowired
	private DisciplinaServico ds;

	@Autowired
	private CredencialServico cs;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAutoEstudoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			// 1
			Credencial c = new Credencial();
			c.setEmail("p@mal.com");
			c.setSenha("fefew");
			c.setUsername("pedrosss");

			Estudante e = new Estudante();
			e.setInstituicao("fsdfsd");
			e.setDataNascimento("01/02/1323");
			e.setDescricao("ffefrere");
			e.setEscolaridade(EnumTipoEscolaridade.ENSINO_MEDIO);
			e.setNome("pedro");
			e.setSexo(EnumSexo.MASCULINO);
			c.setEstudante(e);
			this.cs.inserir(c);


			// 2
			Credencial c1 = new Credencial();
			c1.setEmail("p@mswdal.com");
			c1.setSenha("fefewwdw");
			c1.setUsername("rosss");

			Estudante e1 = new Estudante();
			e1.setInstituicao("fsdfsd");
			e1.setDataNascimento("21/02/1323");
			e1.setDescricao("ffefrere");
			e1.setEscolaridade(EnumTipoEscolaridade.ENSINO_MEDIO);
			e1.setNome("peddasd");
			e1.setSexo(EnumSexo.MASCULINO);
			c1.setEstudante(e1);
			this.cs.inserir(c1);

//
			Disciplina d = new Disciplina();

			d.setAssunto("dfdf");
			d.setDataDisciplina("34/24/333");
			d.setQtdEstudantes(1);
			d.setDisciplina("t34t3");
			d.getListaEstudantes().add(e);
			e.getListaDisciplinas().add(d);



			Disciplina d1 = new Disciplina();

			d1.setAssunto("d111");
			d1.setDataDisciplina("11/24/333");
			d1.setQtdEstudantes(1);
			d1.setDisciplina("fwefwe");
			c.getEstudante().getListaDisciplinas().add(d1);
			d1.getListaEstudantes().add(e);
			e.getListaDisciplinas().add(d1);
			
			
			//this.ds.inserir(d1);
			this.es.inserir(e);
			
			
		};
	}
}
