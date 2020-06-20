package com.azienda.esercizioJpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ContoCorrente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id = null;
	String numeroConto = null;
	Float saldo = null;
	
	@ManyToOne
	@JoinColumn(name = "bancaConto")
	private Banca banca = null;
	
	@ManyToMany
	@JoinTable(name = "ContiCorrenti_Persone",joinColumns = @JoinColumn(name="contocorrenteId"),inverseJoinColumns = @JoinColumn(name="personaId"))
	private List<Persona> persone = null;
	
	
	public ContoCorrente() {
		this(null,null,null);
	}
	
	public ContoCorrente(String numeroConto, Float saldo, List<Persona> persone) {
		persone = new ArrayList<Persona>();
		this.numeroConto = numeroConto;
		this.saldo = saldo;
		this.persone = persone; 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroConto() {
		return numeroConto;
	}

	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public Banca getBanca() {
		return banca;
	}

	public void setBanca(Banca banca) {
		this.banca = banca;
	}

	public List<Persona> getPersone() {
		return persone;
	}

	public void setPersone(List<Persona> persone) {
		this.persone = persone;
	}
	
}
