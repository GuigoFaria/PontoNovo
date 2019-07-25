package com.ponto.novo.pontonovo.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Ponto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPonto;
	@ManyToOne
	private Funcionario funcionario;
	private LocalDate dataPonto;
	private LocalTime horarioInicioExpediente;
	private LocalTime horarioInicioAlmoco;
	private LocalTime horarioFimAlmoco;
	private LocalTime horarioFimExpediente;
	private Long tempoExpediente;
	
	public Ponto() {
		
	}

	public int getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDate getDataPonto() {
		return dataPonto;
	}

	public void setDataPonto(LocalDate dataPonto) {
		this.dataPonto = dataPonto;
	}

	public LocalTime getHorarioInicioExpediente() {
		return horarioInicioExpediente;
	}

	public void setHorarioInicioExpediente(LocalTime horarioInicioExpediente) {
		this.horarioInicioExpediente = horarioInicioExpediente;
	}

	public LocalTime getHorarioFimAlmoco() {
		return horarioFimAlmoco;
	}

	public void setHorarioFimAlmoco(LocalTime horarioFimAlmoco) {
		this.horarioFimAlmoco = horarioFimAlmoco;
	}

	public LocalTime getHorarioFimExpediente() {
		return horarioFimExpediente;
	}

	public void setHorarioFimExpediente(LocalTime horarioFimExpediente) {
		this.horarioFimExpediente = horarioFimExpediente;
	}

	public Long getTempoExpediente() {
		return tempoExpediente;
	}

	public void setTempoExpediente(Long tempoExpediente) {
		this.tempoExpediente = tempoExpediente;
	}

	public LocalTime getHorarioInicioAlmoco() {
		return horarioInicioAlmoco;
	}

	public void setHorarioInicioAlmoco(LocalTime horarioInicioAlmoco) {
		this.horarioInicioAlmoco = horarioInicioAlmoco;
	}
}
