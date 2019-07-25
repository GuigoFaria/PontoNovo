package com.ponto.novo.pontonovo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.novo.pontonovo.entities.Empresa;
import com.ponto.novo.pontonovo.exceptions.ResourceNotFoundException;
import com.ponto.novo.pontonovo.repository.EmpresaRepository;

@RestController
public class EmpresaController {

	private final EmpresaRepository empresaRepository;

	EmpresaController(EmpresaRepository repository) {
		this.empresaRepository = repository;
	}

	@GetMapping("/empresas")
	public List<Empresa> listaEmpresas() {
		return empresaRepository.findAll();
	}

	@GetMapping("/empresas/{id}")
	public Empresa empresaById(@PathVariable int id) {
		return empresaRepository.getOne(id);
	}

	@PostMapping("/empresas")
	public Empresa cadastroEmpresa(@RequestBody Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	@DeleteMapping("/empresas")
	public void deletaTodasEmpresas() {
		empresaRepository.deleteAll();

	}

	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<?> deletaEmpresaPorId(@PathVariable int id) {
		return empresaRepository.findById(id).map(question -> {
			empresaRepository.delete(question);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada id: " + id));

	}

}
