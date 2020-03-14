package com.pedrohnf688.api.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "credencial")
@Table
public class Credencial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", nullable = false)
	@NotBlank(message = "O campo username não pode ser vazio.")
	private String username;

	@Column(name = "email", nullable = false)
	@NotBlank(message = "O campo email não pode ser vazio.")
	private String email;

	@Column(name = "senha", nullable = false)
	@NotBlank(message = "O campo senha não pode ser vazio.")
	private String senha;

	@OneToOne
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private Estudante estudante;

}
