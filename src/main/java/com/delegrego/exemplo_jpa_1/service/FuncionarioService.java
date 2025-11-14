package com.delegrego.exemplo_jpa_1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_jpa_1.entity.Funcionario;
import com.delegrego.exemplo_jpa_1.repo.DepartamentoRepository;
import com.delegrego.exemplo_jpa_1.repo.FuncionarioRepository;

/**
 * Serviço para gerenciar operações relacionadas a Departamentos. Fornece
 * métodos para criar, ler, atualizar e deletar departamentos, além de
 * funcionalidades de pesquisa.
 */

// Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service
public class FuncionarioService {

	// Autowired injeta automaticamente a interface de repositório que acessa o
	// banco de dados
	@Autowired
	private FuncionarioRepository funcionarioRepo;

	@Autowired
	private DepartamentoRepository departamentoRepo;

	/**
	 * Create: Cadastra um novo funcionário no sistema.
	 * 
	 * @param funcionario - O funcionário a ser cadastrado.
	 * @throws RuntimeException se já existir um funcionário com o mesmo CPF ou se o
	 *                          departamento não existir.
	 */
	public void cadastrarFuncionario(Funcionario funcionario) {

		if (funcionarioRepo.existsByEmail(funcionario.getEmail())) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		if (!departamentoRepo.existsById(funcionario.getDepartamento().getIdDepartamento())) {
			throw new RuntimeException("Departamento não existe");
		}

		funcionarioRepo.save(funcionario);
	}

	/**
	 * Read: Lista todos os funcionários cadastrados no sistema.
	 * 
	 * @return Uma lista de funcionários.
	 */
	public List<Funcionario> listarFuncionarios() {
		return funcionarioRepo.findAll();
	}

	/**
	 * Read pesquisa parcial: Pesquisa funcionários pelo nome ou email.
	 * 
	 * @param pesquisa - O termo de pesquisa (nome ou email).
	 * @return Uma lista de funcionários que correspondem ao termo de pesquisa.
	 */
	public List<Funcionario> pesquisarFuncionarios(String pesquisa) {
		return funcionarioRepo.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(pesquisa, pesquisa);
	}

	/**
	 * Read por ID: Obtém os detalhes de um funcionário específico pelo seu ID.
	 * 
	 * @param id - O ID do funcionário a ser obtido.
	 * @return O Funcionário.
	 * @throws RuntimeException se o funcionário não existir.
	 */
	public Funcionario obterFuncionarioPorId(int id) {
		return funcionarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não existe"));
	}

	/**
	 * Update: Atualiza as informações de um funcionário existente.
	 * 
	 * @param funcionario - O funcionário com as informações atualizadas.
	 * @throws RuntimeException se o funcionário não existir, se já existir outro
	 *                          funcionário com o mesmo CPF, ou se o departamento
	 *                          não existir.
	 */
	public void atualizarFuncionario(Funcionario funcionario) {

		if (!funcionarioRepo.existsById(funcionario.getIdFuncionario())) {
			throw new RuntimeException("Funcionário não existe");
		}

		if (funcionarioRepo.existsByEmailAndIdFuncionarioNot(funcionario.getEmail(), funcionario.getIdFuncionario())) {
			throw new RuntimeException("Usuário com esse email já existe");
		}

		if (!departamentoRepo.existsById(funcionario.getDepartamento().getIdDepartamento())) {
			throw new RuntimeException("Departamento não existe");
		}

		funcionarioRepo.save(funcionario);
	}

	/**
	 * Delete: Deleta um funcionário pelo seu ID.
	 * 
	 * @param id - O ID do funcionário a ser deletado.
	 * @throws RuntimeException se o funcionário não existir.
	 */
	public void deletarFuncionario(int id) {

		if (!funcionarioRepo.existsById(id)) {
			throw new RuntimeException("Funcionário não existe");
		}

		funcionarioRepo.deleteById(id);
	}

}