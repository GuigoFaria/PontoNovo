package com.ponto.novo.pontonovo.controllers;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.novo.pontonovo.entities.Funcionario;
import com.ponto.novo.pontonovo.entities.Ponto;
import com.ponto.novo.pontonovo.exceptions.ResourceNotFoundException;
import com.ponto.novo.pontonovo.repository.FuncionarioRepository;
import com.ponto.novo.pontonovo.repository.PontoRepository;

@RestController
public class PontoController {
	private final PontoRepository pontoRepository;
	private final FuncionarioRepository funcionarioRepository;

	PontoController(PontoRepository repository, FuncionarioRepository fr) {
		this.pontoRepository = repository;
		this.funcionarioRepository = fr;
	}

	@GetMapping("/pontos")
	public List<Ponto> listaPontos() {
		return pontoRepository.findAll();
	}

	@GetMapping("/pontos/{id}")
	public Ponto getPontoPorId(@PathVariable int id) {
		return pontoRepository.getOne(id);
	}

	@PostMapping("/pontos/{idFuncionario}")
	public Ponto createPonto(@PathVariable int idFuncionario, Ponto ponto) throws ResourceNotFoundException {
		if (funcionarioRepository.existsById(idFuncionario)) {
			Funcionario funcionario = new Funcionario();
			funcionario.setIdFuncionario(idFuncionario);

			ponto.setDataPonto(LocalDate.now());
			ponto.setFuncionario(funcionario);
			ponto.setHorarioInicioExpediente(LocalTime.now());
			return pontoRepository.save(ponto);
		} else {
			return null;
		}

	}

	@PutMapping("/pontos/almocoIda/{id}")
	public Ponto almoçoIdaPonto(@PathVariable int id) {
		return pontoRepository.findById(id).map(pontoGet -> {
			pontoGet.setHorarioInicioAlmoco(LocalTime.now());
			return pontoRepository.save(pontoGet);
		}).orElseThrow(() -> new ResourceNotFoundException("Ponto não existe id: " + id));

	}

	@PutMapping("/pontos/almocoVolta/{id}")
	public Ponto almocoVoltaPonto(@PathVariable int id) {
		return pontoRepository.findById(id).map(pontoGet -> {
			pontoGet.setHorarioFimAlmoco(LocalTime.now());
			return pontoRepository.save(pontoGet);
		}).orElseThrow(() -> new ResourceNotFoundException("Ponto não existe id: " + id));

	}

	@PutMapping("/pontos/fimExpediente/{id}")
	public Ponto fimExpedientePonto(@PathVariable int id) {
		return pontoRepository.findById(id).map(pontoGet -> {
			pontoGet.setHorarioFimExpediente(LocalTime.now());
			long intervaloPreAlmoco = ChronoUnit.HOURS.between(pontoGet.getHorarioInicioExpediente(),
					pontoGet.getHorarioInicioAlmoco());
			long intervaloPosAlmoco = ChronoUnit.HOURS.between(pontoGet.getHorarioFimAlmoco(),
					pontoGet.getHorarioFimExpediente());
			pontoGet.setTempoExpediente(intervaloPreAlmoco + intervaloPosAlmoco);
			return pontoRepository.save(pontoGet);
		}).orElseThrow(() -> new ResourceNotFoundException("Ponto não existe id: " + id));

	}

	// Excluir depois
	@DeleteMapping("/pontos")
	public void deletaTodosPontos() {
		pontoRepository.deleteAll();
	}

	@DeleteMapping("/pontos/{id}")
	public ResponseEntity<?> deletaPontoPorId(@PathVariable int id) {
		return pontoRepository.findById(id).map(pontoGet -> {
			pontoRepository.delete(pontoGet);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado id: " + id));
	}
}