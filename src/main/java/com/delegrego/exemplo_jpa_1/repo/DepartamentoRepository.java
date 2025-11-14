package com.delegrego.exemplo_jpa_1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_jpa_1.entity.Departamento;

/**
 * Repositório JPA para a entidade Departamento. Extende JpaRepository para
 * fornecer operações CRUD e outras funcionalidades. O JpaRepository é
 * parametrizado com a entidade Departamento e o tipo da chave primária
 * (Integer).
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	// Derived queries

	/**
	 * Busca departamentos cujo nome contenha a string especificada, ignorando
	 * maiúsculas e minúsculas.
	 * 
	 * @param nomeDepartamento - Parte do nome do departamento a ser buscada.
	 * @return Lista de departamentos que correspondem aos critérios de busca.
	 */
	List<Departamento> findByNomeDepartamentoContainingIgnoreCase(String nomeDepartamento);

}
