package com.delegrego.exemplo_jpa_1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delegrego.exemplo_jpa_1.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	// Derived queries

	/**
	 * Busca funcionários cujo nome ou email contenham a string especificada,
	 * ignorando maiúsculas e minúsculas.
	 * 
	 * @param nome  - Parte do nome a ser buscada.
	 * @param email - Parte do email a ser buscada.
	 * @return Lista de funcionários que correspondem aos critérios de busca.
	 */
	List<Funcionario> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email);

	/**
	 * Verifica se existe um funcionário com o email especificado.
	 * 
	 * @param email - Email a ser verificado.
	 * @return true se existir um funcionário com o email, false caso contrário.
	 */
	boolean existsByEmail(String email);

	/**
	 * Verifica se existe um funcionário com o mesmo email, excluindo um ID
	 * específico. Útil para validação ao atualizar um funcionário.
	 * 
	 * @param email - Email a ser verificado.
	 * @param id    - ID do funcionário a ser excluído da verificação.
	 * @return true se existir outro funcionário com o mesmo email, false caso
	 *         contrário.
	 */
	boolean existsByEmailAndIdFuncionarioNot(String email, int id);

	/**
	 * Verifica se existe algum funcionário associado a um departamento específico.
	 * Útil para validação antes de excluir um departamento.
	 * 
	 * @param idDepartamento - ID do departamento a ser verificado.
	 * @return true se existir pelo menos um funcionário no departamento, false caso
	 *         contrário.
	 */
	boolean existsByDepartamentoIdDepartamento(int idDepartamento);
}
