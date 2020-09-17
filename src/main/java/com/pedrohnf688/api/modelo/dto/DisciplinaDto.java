package com.pedrohnf688.api.modelo.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DisciplinaDto {

	@NotBlank(message = "O campo disciplina não pode ser vazio.")
	private String disciplina;

	@NotBlank(message = "O campo assunto não pode ser vazio.")
	private String assunto;

	@NotBlank(message = "O campo horario não pode ser vazio.")
	private String horario;
}
