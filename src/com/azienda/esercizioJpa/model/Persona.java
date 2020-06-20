package com.azienda.esercizioJpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id = null;
	String nome = null;
	String cognome = null;
	
	@ManyToMany(mappedBy = "persone")
	private List <ContoCorrente> conti = null;
	
	
	public Persona() {
		this(null,null,null);
	}

	public Persona(String nome, String cognome, List<ContoCorrente> conti) {
		conti = new ArrayList<ContoCorrente>();
		this.nome = nome;
		this.cognome = cognome;
		this.conti = conti;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<ContoCorrente> getConti() {
		return conti;
	}

	public void setConti(List<ContoCorrente> conti) {
		this.conti = conti;
	}
	
	
}
