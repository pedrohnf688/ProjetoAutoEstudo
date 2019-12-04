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
	@NotBlank(message = "O campo finalizada não pode ser vazio.")
	private boolean finalizada;

	@Column(name = "prioridade", nullable = false)
	@NotNull(message = "O campo prioridade não pode ser vazio.")
	@Enumerated(EnumType.STRING)
	private EnumPrioridade prioridade;

	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	public Tarefa() {
		super();
	}

	public Tarefa(@NotBlank(message = "O campo tituloTarefa não pode ser vazio.") String tituloTarefa,
			@NotBlank(message = "O campo descricaoTarefa não pode ser vazio.") String descricaoTarefa,
			@NotBlank(message = "O campo inicioTarefa não pode ser vazio.") String inicioTarefa,
			@NotBlank(message = "O campo terminoTarefa não pode ser vazio.") String terminoTarefa,
			@NotBlank(message = "O campo finalizada não pode ser vazio.") boolean finalizada,
			@NotBlank(message = "O campo prioridade não pode ser vazio.") EnumPrioridade prioridade,
			Disciplina disciplina) {
		super();
		this.tituloTarefa = tituloTarefa;
		this.descricaoTarefa = descricaoTarefa;
		this.inicioTarefa = inicioTarefa;
		this.terminoTarefa = terminoTarefa;
		this.finalizada = finalizada;
		this.prioridade = prioridade;
		this.disciplina = disciplina;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTituloTarefa() {
		return tituloTarefa;
	}

	public void setTituloTarefa(String tituloTarefa) {
		this.tituloTarefa = tituloTarefa;
	}

	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}

	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}

	public String getInicioTarefa() {
		return inicioTarefa;
	}

	public void setInicioTarefa(String inicioTarefa) {
		this.inicioTarefa = inicioTarefa;
	}

	public String getTerminoTarefa() {
		return terminoTarefa;
	}

	public void setTerminoTarefa(String terminoTarefa) {
		this.terminoTarefa = terminoTarefa;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public EnumPrioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(EnumPrioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", tituloTarefa=" + tituloTarefa + ", descricaoTarefa=" + descricaoTarefa
				+ ", inicioTarefa=" + inicioTarefa + ", terminoTarefa=" + terminoTarefa + ", finalizada=" + finalizada
				+ ", prioridade=" + prioridade + ", disciplina=" + disciplina + "]";
	}

}
