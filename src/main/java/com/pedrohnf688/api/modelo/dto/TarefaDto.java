package com.pedrohnf688.api.modelo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pedrohnf688.api.modelo.enums.EnumPrioridade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaDto {

	@NotBlank(message = "O campo tituloTarefa não pode ser vazio.")
	private String tituloTarefa;

	@NotBlank(message = "O campo descricaoTarefa não pode ser vazio.")
	private String descricaoTarefa;

	@NotBlank(message = "O campo inicioTarefa não pode ser vazio.")
	private String inicioTarefa;

	@NotBlank(message = "O campo terminoTarefa não pode ser vazio.")
	private String terminoTarefa;

	private boolean finalizada;

	@NotNull(message = "O campo prioridade não pode ser vazio.")
	private EnumPrioridade prioridade;

}
