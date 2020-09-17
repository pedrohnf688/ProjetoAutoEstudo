package com.pedrohnf688.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.Disciplina;
import com.pedrohnf688.api.modelo.Estudante;
import com.pedrohnf688.api.modelo.Grupo;
import com.pedrohnf688.api.modelo.enums.EnumPerfil;
import com.pedrohnf688.api.modelo.enums.EnumSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoEscolaridade;
import com.pedrohnf688.api.servico.CredencialServico;
import com.pedrohnf688.api.servico.DisciplinaServico;
import com.pedrohnf688.api.servico.GrupoServico;

@SpringBootApplication
public class ProjetoAutoEstudoApplication {

	@Autowired
	private DisciplinaServico ds;

	@Autowired
	private CredencialServico cs;

	@Autowired
	private GrupoServico gs;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAutoEstudoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			Credencial c = new Credencial();
			c.setId(1);
			c.setEmail("p@mal.com");
			c.setSenha("fefew");
			c.setUsername("pedrohnf");

			Estudante e = new Estudante();
			e.setId(1);
			e.setInstituicao("ufrn");
			e.setDataNascimento("01/02/1323");
			e.setDescricao("ffefrere");
			e.setEscolaridade(EnumTipoEscolaridade.ENSINO_MEDIO);
			e.setNome("pedro");
			e.setPontos(100);
			e.setStatus(EnumPerfil.ESTUDANTE.toString());
			e.setSexo(EnumSexo.MASCULINO);
			c.setEstudante(e);
			this.cs.inserir(c);

			Credencial c1 = new Credencial();
			c1.setId(2);
			c1.setEmail("p@mswdal.com");
			c1.setSenha("fefewwdw");
			c1.setUsername("roberto");

			Estudante e1 = new Estudante();
			e1.setId(2);
			e1.setInstituicao("unp");
			e1.setDataNascimento("21/02/1323");
			e1.setDescricao("ffefrere");
			e1.setEscolaridade(EnumTipoEscolaridade.ENSINO_MEDIO);
			e1.setNome("roberto");
			e1.setPontos(100);
			e1.setStatus(EnumPerfil.ESTUDANTE.toString());
			e1.setSexo(EnumSexo.MASCULINO);
			c1.setEstudante(e1);
			this.cs.inserir(c1);

			Disciplina d = new Disciplina();
			d.setId(1);
			d.setAssunto("matematica");
			d.setDataDisciplina(new Date());
			d.setQtdEstudantes(1);
			d.setHorario("08:00");
			d.setDisciplina("matematica");
			d.getListaEstudantes().add(e1);
			this.ds.inserir(d);

			Disciplina d1 = new Disciplina();
			d1.setId(2);
			d1.setAssunto("portugues");
			d1.setDataDisciplina(new Date());
			d1.setQtdEstudantes(1);
			d1.setHorario("09:00");
			d1.setDisciplina("portugues");
			d1.getListaEstudantes().add(e);
			this.ds.inserir(d1);

			Grupo g = new Grupo();
			g.setId(1);
			g.setDataGrupo("01/02/1222");
			g.getListaEstudantes().add(e);
			g.setQtdEstudantes(1);
			g.setTitulo("estudando...");
			this.gs.inserir(g);

		};
	}
}