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

	@OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<Conversa> listaConversas;

}
