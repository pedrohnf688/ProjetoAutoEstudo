package com.pedrohnf688.api.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "disciplina")
@Table
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disciplina")
	private Integer id;

	@Column(name = "disciplina", nullable = false)
	@NotBlank(message = "O campo disciplina não pode ser vazio.")
	private String disciplina;

	@Column(name = "assunto", nullable = false)
	@NotBlank(message = "O campo assunto não pode ser vazio.")
	private String assunto;

	@Column(name = "dataDisciplina", nullable = false)
	@NotBlank(message = "O campo dataDisciplina não pode ser vazio.")
	private String dataDisciplina;

	@Column(name = "qtdEstudantes")
	@NotNull(message = "O campo qtdEstudantes não pode ser vazio.")
	private int qtdEstudantes;

	@ManyToMany
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "disciplina_estudante", joinColumns = {
			@JoinColumn(name = "disciplina_id", referencedColumnName = "id_disciplina") }, inverseJoinColumns = {
					@JoinColumn(name = "estudante_id") })
	@JsonIgnore
	private List<Estudante> listaEstudantes = new ArrayList<Estudante>();

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<Tarefa> listaTarefas;

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<Artigo> listaArtigos;

}
