package com.pedrohnf688.api.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pedrohnf688.api.modelo.enums.EnumPrioridade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tarefa")
@Table
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tituloTarefa", nullable = false)
	@NotBlank(message = "O campo tituloTarefa não pode ser vazio.")
	private String tituloTarefa;

	@Column(name = "descricaoTarefa", nullable = false)
	@NotBlank(message = "O campo descricaoTarefa não pode ser vazio.")
	private String descricaoTarefa;

	@Column(name = "inicioTarefa", nullable = false)
	@NotBlank(message = "O campo inicioTarefa não pode ser vazio.")
	private String inicioTarefa;

	@Column(name = "terminoTarefa", nullable = false)
	@NotBlank(message = "O campo terminoTarefa não pode ser vazio.")
	private String terminoTarefa;

	@Column(name = "finalizada", nullable = false)
	private boolean finalizada;

	@Column(name = "prioridade", nullable = false)
	@NotNull(message = "O campo prioridade não pode ser vazio.")
	@Enumerated(EnumType.STRING)
	private EnumPrioridade prioridade;

	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

}
