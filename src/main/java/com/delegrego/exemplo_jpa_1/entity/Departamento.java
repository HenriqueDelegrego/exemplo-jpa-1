package com.delegrego.exemplo_jpa_1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa a entidade Departamento no banco de dados.
 */

//Indica que esta classe é uma entidade JPA
@Entity

//Define o nome da tabela no banco de dados que esta entidade representa
@Table(name = "departamento")
public class Departamento {

	// Define o campo 'id' como a chave primária da tabela
	@Id

	// Especifica que o valor do campo 'id' será gerado automaticamente pelo banco
	// de dados
	// (AUTO_INCREMENT)
	// IDENTITY é o tipo utilizado no MySQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Mapeia o campo 'idDepartamento' para a coluna 'id_departamento' na tabela
	@Column(name = "id_departamento")
	private int idDepartamento;

	@Column(name = "nome_departamento")
	private String nomeDepartamento;

	public Departamento(int idDepartamento, String nomeDepartamento) {
		this.idDepartamento = idDepartamento;
		this.nomeDepartamento = nomeDepartamento;
	}

	// Construtor padrão (necessário para o JPA)
	public Departamento() {

	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", nomeDepartamento=" + nomeDepartamento + "]";
	}

}
