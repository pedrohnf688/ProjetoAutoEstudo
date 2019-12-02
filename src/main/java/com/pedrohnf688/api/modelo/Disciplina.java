package com.pedrohnf688.api.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "disciplina")
@Table
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disciplina")
	private Integer id;

	@Column(name = "disciplina", nullable = false)
	@NotNull(message = "O campo disciplina não pode ser vazio.")
	private String disciplina;

	@Column(name = "assunto", nullable = false)
	@NotNull(message = "O campo assunto não pode ser vazio.")
	private String assunto;

	@Column(name = "dataDisciplina", nullable = false)
	@NotNull(message = "O campo dataDisciplina não pode ser vazio.")
	private String dataDisciplina;

	@Column(name = "qtdEstudantes")
	private int qtdEstudantes;

	@ManyToMany
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "disciplina_estudante", joinColumns = {
			@JoinColumn(name = "disciplina_id", referencedColumnName = "id_disciplina") }, inverseJoinColumns = {
					@JoinColumn(name = "estudante_id") })
	private List<Estudante> listaEstudantes = new ArrayList<Estudante>();

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<Tarefa> listaTarefas;

	public Disciplina() {
		super();
	}

	public Disciplina(@NotNull(message = "O campo disciplina não pode ser vazio.") String disciplina,
			@NotNull(message = "O campo assunto não pode ser vazio.") String assunto,
			@NotNull(message = "O campo dataDisciplina não pode ser vazio.") String dataDisciplina, int qtdEstudantes,
			List<Estudante> listaEstudantes, List<Tarefa> listaTarefas) {
		super();
		this.disciplina = disciplina;
		this.assunto = assunto;
		this.dataDisciplina = dataDisciplina;
		this.qtdEstudantes = qtdEstudantes;
		this.listaEstudantes = listaEstudantes;
		this.listaTarefas = listaTarefas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDataDisciplina() {
		return dataDisciplina;
	}

	public void setDataDisciplina(String dataDisciplina) {
		this.dataDisciplina = dataDisciplina;
	}

	public int getQtdEstudantes() {
		return qtdEstudantes;
	}

	public void setQtdEstudantes(int qtdEstudantes) {
		this.qtdEstudantes = qtdEstudantes;
	}

	public List<Estudante> getListaEstudantes() {
		return listaEstudantes;
	}

	public void setListaEstudantes(List<Estudante> listaEstudantes) {
		this.listaEstudantes = listaEstudantes;
	}

	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

	public void addEstudante(Estudante e) {
		listaEstudantes.add(e);
		e.getListaDisciplinas().add(this);
	}

	public void removeEstudante(Estudante e) {
		listaEstudantes.remove(e);
		e.getListaDisciplinas().remove(this);
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", disciplina=" + disciplina + ", assunto=" + assunto + ", dataDisciplina="
				+ dataDisciplina + ", qtdEstudantes=" + qtdEstudantes + ", listaEstudantes=" + listaEstudantes
				+ ", listaTarefas=" + listaTarefas + "]";
	}

}
