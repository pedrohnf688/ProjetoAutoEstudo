package com.pedrohnf688.api.modelo.dto;

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
public class EstudanteDto {

	@NotBlank(message = "O campo nome não pode ser vazio.")
	private String nome;

	@NotBlank(message = "O campo instituicao não pode ser vazio.")
	private String instituicao;

	private String descricao;

	@NotNull(message = "O campo sexo não pode ser vazio.")
	private EnumSexo sexo;

	@NotBlank(message = "O campo dataNascimento não pode ser vazio.")
	private String dataNascimento;

	@NotNull(message = "O campo escolaridade não pode ser vazio.")
	private EnumTipoEscolaridade escolaridade;

	@NotBlank(message = "O campo username não pode ser vazio.")
	private String username;

	@NotBlank(message = "O campo email não pode ser vazio.")
	private String email;

	@NotBlank(message = "O campo senha não pode ser vazio.")
	private String senha;

	@NotNull(message = "O campo pontos não pode ser nulo.")
	private Integer pontos;

	@NotBlank(message = "O campo status não pode ser vazio.")
	private String status;
}
