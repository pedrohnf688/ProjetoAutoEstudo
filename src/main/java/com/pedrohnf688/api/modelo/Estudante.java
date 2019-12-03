package com.pedrohnf688.api.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pedrohnf688.api.modelo.enums.EnumSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoEscolaridade;

@Entity(name = "estudante")
@Table
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", nullable = false)
	@NotNull(message = "O campo nome não pode ser vazio.")
	private String nome;

	@Column(name = "instituicao", nullable = false)
	@NotNull(message = "O campo instituicao não pode ser vazio.")
	private String instituicao;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "sexo", nullable = false)
	@NotNull(message = "O campo sexo não pode ser vazio.")
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;

	@Column(name = "dataNascimento", nullable = false)
	@NotNull(message = "O campo dataNascimento não pode ser vazio.")
	private String dataNascimento;

	@Column(name = "escolaridade", nullable = false)
	@NotNull(message = "O campo escolaridade não pode ser vazio.")
	@Enumerated(EnumType.STRING)
	private EnumTipoEscolaridade escolaridade;

	@ManyToMany(mappedBy = "listaEstudantes")
	private List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();

	@ManyToMany(mappedBy = "listaEstudantes")
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	public Estudante() {
		super();
	}

	public Estudante(@NotNull(message = "O campo nome não pode ser vazio.") String nome,
			@NotNull(message = "O campo instituicao não pode ser vazio.") String instituicao, String descricao,
			@NotNull(message = "O campo sexo não pode ser vazio.") EnumSexo sexo,
			@NotNull(message = "O campo dataNascimento não pode ser vazio.") String dataNascimento,
			@NotNull(message = "O campo escolaridade não pode ser vazio.") EnumTipoEscolaridade escolaridade,
			List<Disciplina> listaDisciplinas, List<Grupo> listaGrupos) {
		super();
		this.nome = nome;
		this.instituicao = instituicao;
		this.descricao = descricao;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.escolaridade = escolaridade;
		this.listaDisciplinas = listaDisciplinas;
		this.listaGrupos = listaGrupos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnumTipoEscolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(EnumTipoEscolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public List<Disciplina> getListaDisciplinas() {
		return listaDisciplinas;
	}

	public void setListaDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	@Override
	public String toString() {
		return "Estudante [id=" + id + ", nome=" + nome + ", instituicao=" + instituicao + ", descricao=" + descricao
				+ ", sexo=" + sexo + ", dataNascimento=" + dataNascimento + ", escolaridade=" + escolaridade
				+ ", listaDisciplinas=" + listaDisciplinas + ", listaGrupos=" + listaGrupos + "]";
	}

}
