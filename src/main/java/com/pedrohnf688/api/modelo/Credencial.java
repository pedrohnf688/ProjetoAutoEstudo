package com.pedrohnf688.api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

@Entity(name = "credencial")
@Table
public class Credencial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", nullable = false)
	@NotNull(message = "O campo username não pode ser vazio.")
	private String username;

	@Column(name = "email", nullable = false)
	@NotNull(message = "O campo email não pode ser vazio.")
	private String email;

	@Column(name = "senha", nullable = false)
	@NotNull(message = "O campo senha não pode ser vazio.")
	private String senha;

	@OneToOne
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private Estudante estudante;

	public Credencial() {
		super();
	}

	public Credencial(@NotNull(message = "O campo username não pode ser vazio.") String username,
			@NotNull(message = "O campo email não pode ser vazio.") String email,
			@NotNull(message = "O campo senha não pode ser vazio.") String senha, Estudante estudante) {
		super();
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.estudante = estudante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	@Override
	public String toString() {
		return "Credencial [id=" + id + ", username=" + username + ", email=" + email + ", senha=" + senha
				+ ", estudante=" + estudante + "]";
	}

}
