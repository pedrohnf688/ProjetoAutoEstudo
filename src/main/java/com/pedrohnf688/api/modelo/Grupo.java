package com.pedrohnf688.api.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "grupo")
@Table
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo")
	private Integer id;

	@Column(name = "titulo", nullable = false)
	@NotBlank(message = "O campo titulo não pode ser vazio.")
	private String titulo;

	@Column(name = "dataGrupo", nullable = false)
	@NotBlank(message = "O campo dataGrupo não pode ser vazio.")
	private String dataGrupo;

	@Column(name = "qtdEstudantes", nullable = false)
	@NotNull(message = "O campo qtdEstudantes não pode ser vazio.")
	private int qtdEstudantes;

	@ManyToMany
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "grupo_estudante", joinColumns = {
			@JoinColumn(name = "grupo_id", referencedColumnName = "id_grupo") }, inverseJoinColumns = {
					@JoinColumn(name = "estudante_id") })
	@JsonIgnore
	private List<Estudante> listaEstudantes = new ArrayList<Estudante>();

	public Grupo() {
		super();
	}

	public Grupo(@NotBlank(message = "O campo titulo não pode ser vazio.") String titulo,
			@NotBlank(message = "O campo dataGrupo não pode ser vazio.") String dataGrupo,
			@NotBlank(message = "O campo qtdEstudantes não pode ser vazio.") int qtdEstudantes,
			List<Estudante> listaEstudantes) {
		super();
		this.titulo = titulo;
		this.dataGrupo = dataGrupo;
		this.qtdEstudantes = qtdEstudantes;
		this.listaEstudantes = listaEstudantes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataGrupo() {
		return dataGrupo;
	}

	public void setDataGrupo(String dataGrupo) {
		this.dataGrupo = dataGrupo;
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

	public void addEstudante(Estudante e) {
		listaEstudantes.add(e);
		e.getListaGrupos().add(this);
	}

	public void removeEstudante(Estudante e) {
		listaEstudantes.remove(e);
		e.getListaGrupos().remove(this);
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", titulo=" + titulo + ", dataGrupo=" + dataGrupo + ", qtdEstudantes="
				+ qtdEstudantes + ", listaEstudantes=" + listaEstudantes + "]";
	}

}
