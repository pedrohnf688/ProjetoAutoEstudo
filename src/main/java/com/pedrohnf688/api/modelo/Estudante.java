package com.pedrohnf688.api.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pedrohnf688.api.modelo.enums.EnumSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoEscolaridade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "estudante")
@Table
public class Estudante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", nullable = false)
	@NotBlank(message = "O campo nome não pode ser vazio.")
	private String nome;

	@Column(name = "instituicao", nullable = false)
	@NotBlank(message = "O campo instituicao não pode ser vazio.")
	private String instituicao;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "sexo", nullable = false)
	@NotNull(message = "O campo sexo não pode ser vazio.")
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;

	@Column(name = "dataNascimento", nullable = false)
	@NotBlank(message = "O campo dataNascimento não pode ser vazio.")
	private String dataNascimento;

	@Column(name = "pontos", nullable = false)
	@NotNull(message = "O campo pontos não pode ser nulo.")
	private Integer pontos;

	@Column(name = "escolaridade", nullable = false)
	@NotNull(message = "O campo escolaridade não pode ser vazio.")
	@Enumerated(EnumType.STRING)
	private EnumTipoEscolaridade escolaridade;

	@ManyToMany(mappedBy = "listaEstudantes")
	private List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();

	@ManyToMany(mappedBy = "listaEstudantes")
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

}
