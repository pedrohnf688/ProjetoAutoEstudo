package com.pedrohnf688.api.modelo.dto;

import javax.validation.constraints.NotBlank;

public class DisciplinaDto {

	@NotBlank(message = "O campo disciplina não pode ser vazio.")
	private String disciplina;

	@NotBlank(message = "O campo assunto não pode ser vazio.")
	private String assunto;

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

	@Override
	public String toString() {
		return "DisciplinaDto [disciplina=" + disciplina + ", assunto=" + assunto + "]";
	}

}
