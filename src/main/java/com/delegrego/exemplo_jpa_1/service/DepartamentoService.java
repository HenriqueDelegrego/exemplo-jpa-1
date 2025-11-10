package com.delegrego.exemplo_jpa_1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delegrego.exemplo_jpa_1.entity.Departamento;
import com.delegrego.exemplo_jpa_1.repo.DepartamentoRepository;
import com.delegrego.exemplo_jpa_1.repo.FuncionarioRepository;

/**
 * Serviço para gerenciar operações relacionadas a Departamentos. Inclui métodos
 * para criar, ler, atualizar e deletar departamentos, além de validações
 * específicas.
 */

// Indica que esta classe é um serviço do Spring (camada de lógica de negócio)
@Service
public class DepartamentoService {

	// Autowired injeta automaticamente a interface de repositório que acessa o
	// banco de dados
	@Autowired
	private DepartamentoRepository departamentoRepo;

	@Autowired
	private FuncionarioRepository funcionarioRepo;

	/**
	 * Create: Cadastra um novo departamento no sistema.
	 * 
	 * @param departamento - O departamento a ser cadastrado.
	 */
	public void cadastrarDepartamento(Departamento departamento) {
		departamentoRepo.save(departamento);
	}

	/**
	 * Read: Lista todos os departamentos cadastrados no sistema.
	 * 
	 * @return Uma lista de departamentos.
	 */
	public List<Departamento> listarDepartamentos() {
		return departamentoRepo.findAll();
	}

	/**
	 * Read pesquisa parcial: Pesquisa departamentos pelo nome, permitindo buscas
	 * parciais e ignorando maiúsculas e minúsculas.
	 * 
	 * @param pesquisa - A string de pesquisa para o nome do departamento.
	 * @return Uma lista de departamentos que correspondem à pesquisa.
	 */
	public List<Departamento> pesquisarDepartamentos(String pesquisa) {

		return departamentoRepo.findByNmDepartamentoContainingIgnoreCase(pesquisa);

	}

	/**
	 * Read por ID: Obtém os detalhes de um departamento específico pelo seu ID.
	 * 
	 * @param id - O ID do departamento a ser obtido.
	 * @return O Departamento.
	 * @throws RuntimeException se o departamento não existir.
	 */
	public Departamento obterDepartamentoPorId(int id) {

		return departamentoRepo.findById(id).orElseThrow(() -> new RuntimeException("Departamento não existe"));

	}

	/**
	 * Update: Atualiza as informações de um departamento existente.
	 * 
	 * @param departamento - O departamento com as informações atualizadas.
	 * @throws RuntimeException se o departamento não existir.
	 */
	public void atualizarDepartamento(Departamento departamento) {

		if (!departamentoRepo.existsById(departamento.getIdDepartamento())) {
			throw new RuntimeException("Departamento não existe");
		}

		departamentoRepo.save(departamento);
	}

	/**
	 * Delete: Deleta um departamento pelo seu ID.
	 * 
	 * @param id - O ID do departamento a ser deletado.
	 * @throws RuntimeException se o departamento não existir ou se houver
	 *                          funcionários associados.
	 */
	public void deletarDepartamento(int id) {

		if (!departamentoRepo.existsById(id)) {
			throw new RuntimeException("Departamento não existe");
		}

		if (funcionarioRepo.existsByDepartamentoIdDepartamento(id)) {
			throw new RuntimeException("Não pode excluir departamentos com funcionários");
		}

		departamentoRepo.deleteById(id);
	}
}