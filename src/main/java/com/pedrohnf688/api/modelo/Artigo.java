package com.pedrohnf688.api.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "artigo")
@Table
public class Artigo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ntitulo", nullable = false)
	@NotBlank(message = "O campo titulo não pode ser vazio.")
	private String titulo;

	@Column(name = "dataAtualizacao", nullable = false)
	@NotBlank(message = "O campo dataAtualizacao não pode ser vazio.")
	private String dataAtualizacao;

	@Column(name = "subtitulo", nullable = false)
	@NotBlank(message = "O campo subtitulo não pode ser vazio.")
	private String subtitulo;

	@Column(name = "autor", nullable = false)
	@NotBlank(message = "O campo autor não pode ser vazio.")
	private String autor;

	@Column(name = "linkArtigo", nullable = false)
	@NotBlank(message = "O campo linkArtigo não pode ser vazio.")
	private String linkArtigo;

	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	@OneToMany(mappedBy = "artigo", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<Paragrafo> listaParagrafos;

}
