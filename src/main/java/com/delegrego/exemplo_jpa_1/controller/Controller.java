package com.delegrego.exemplo_jpa_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.delegrego.exemplo_jpa_1.entity.Departamento;
import com.delegrego.exemplo_jpa_1.entity.Funcionario;
import com.delegrego.exemplo_jpa_1.service.DepartamentoService;
import com.delegrego.exemplo_jpa_1.service.FuncionarioService;

/**
 * Controlador para executar operações iniciais na aplicação. Implementa
 * CommandLineRunner para executar código após o início da aplicação.
 */

// Indica que esta classe é um componente do Spring
@Component
public class Controller implements CommandLineRunner {

	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public void run(String... args) {

		// CRUD para Departamento

		// Create
		Departamento departamentoNovo1 = new Departamento();
		departamentoNovo1.setNomeDepartamento("Financeiro");
		departamentoService.cadastrarDepartamento(departamentoNovo1);

		Departamento departamentoNovo2 = new Departamento();
		departamentoNovo2.setNomeDepartamento("Desenvolvimento");
		departamentoService.cadastrarDepartamento(departamentoNovo2);

		Departamento departamentoNovo3 = new Departamento();
		departamentoNovo3.setNomeDepartamento("RH");
		departamentoService.cadastrarDepartamento(departamentoNovo3);

		// Read
		System.out.println(departamentoService.listarDepartamentos());

		// Update
		Departamento departamentoAtualizado = new Departamento();
		departamentoAtualizado.setNomeDepartamento("Recursos Humanos");
		departamentoService.atualizarDepartamento(departamentoAtualizado);

		// Delete
		departamentoService.deletarDepartamento(3);

		// CRUD para Funcionario

		// Create

		Funcionario funcionarioNovo = new Funcionario();
		funcionarioNovo.setNome("João");
		funcionarioNovo.setEmail("joao@email.com");
		funcionarioNovo.setSenha("senha_joao");
		funcionarioNovo.setSalario(5000);
		funcionarioNovo.setDepartamento(departamentoNovo1);
		funcionarioService.cadastrarFuncionario(funcionarioNovo);

		// Read
		System.out.println(funcionarioService.listarFuncionarios());

		// Update

		Funcionario funcionarioAtualizado = new Funcionario();
		funcionarioAtualizado.setIdFuncionario(1);
		funcionarioAtualizado.setNome("João Da Silva");
		funcionarioAtualizado.setEmail("joaonovo@email.com");
		funcionarioAtualizado.setSenha("senha_joao_novo");
		funcionarioAtualizado.setSalario(5500);
		funcionarioAtualizado.setDepartamento(departamentoNovo2);
		funcionarioService.atualizarFuncionario(funcionarioAtualizado);

		// Delete
		funcionarioService.deletarFuncionario(1);

	}

}
