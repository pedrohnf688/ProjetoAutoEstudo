package com.pedrohnf688.api.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "paragrafo")
@Table
public class Paragrafo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "topico", nullable = false)
	@NotBlank(message = "O campo topico não pode ser vazio.")
	private String topico;

	@Column(name = "conteudo", nullable = false)
	@NotBlank(message = "O campo conteudo não pode ser vazio.")
	private String conteudo;

	@ManyToOne
	@JoinColumn(name = "artigo_id")
	private Artigo artigo;

}
