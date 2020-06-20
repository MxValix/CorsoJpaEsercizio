package com.azienda.esercizioJpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Banca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id = null;
	
	String nome = null;
	
	@OneToMany(mappedBy = "banca")
	private List<ContoCorrente> conti = null;
	
	public Banca() {
		this(null,null);
	}
	
	public Banca(String nome, List<ContoCorrente> conti) {
		conti = new ArrayList<ContoCorrente>();
		this.nome = nome;
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

	public List<ContoCorrente> getConti() {
		return conti;
	}

	public void setConti(List<ContoCorrente> conti) {
		this.conti = conti;
	}
	
	
}
