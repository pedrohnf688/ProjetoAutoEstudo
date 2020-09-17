package com.pedrohnf688.api.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "conversa")
@Table
public class Conversa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mensagem")
	private String mensagem;

	@Column(name = "nome", nullable = false)
	@NotBlank(message = "O campo nome não pode ser vazio.")
	private String nome;

	@Column(name = "data", nullable = false)
	@NotBlank(message = "O campo data não pode ser vazio.")
	private String data;

	@OneToOne
	private Arquivo foto;

	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;

}