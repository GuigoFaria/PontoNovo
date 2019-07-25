package com.ponto.novo.pontonovo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.novo.pontonovo.entities.Funcionario;
import com.ponto.novo.pontonovo.exceptions.ResourceNotFoundException;
import com.ponto.novo.pontonovo.repository.FuncionarioRepository;

@RestController
public class FuncionarioController {

	private final FuncionarioRepository funcionarioRepository;

	FuncionarioController(FuncionarioRepository repository) {
		this.funcionarioRepository = repository;
	}

	@GetMapping("/funcionarios")
	public List<Funcionario> listaFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@PostMapping("/funcionarios")
	public Funcionario cadastroFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@DeleteMapping("/funcionarios")
	public void deletaTodosFuncionarios() {
		funcionarioRepository.deleteAll();

	}

	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<?> deletaFuncionarioPorId(@PathVariable int id) {
		return funcionarioRepository.findById(id).map(funcionario -> {
			funcionarioRepository.delete(funcionario);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado id: " + id));
	}
}
