package com.azienda.esercizioJpa.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.azienda.esercizioJpa.model.Persona;

public class PersonaDao implements DaoInterface<Persona> {
	private EntityManager em = null;

	public PersonaDao() {
		this(null);
	}

	public PersonaDao (EntityManager em) {
		setManager(em);
	}

	public EntityManager getManager() {
		return em;
	}

	public void setManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Persona persona) {
		em.persist(persona);
	}

	@Override
	public List<Persona> retrieve() {
		List<Persona> persone = em.createQuery("from Banca",Persona.class).getResultList();
		return persone;
	}

	@Override
	public void update(Persona persona) {
		em.persist(persona);
	}

	@Override
	public void delete(Persona persona) {
		em.remove(persona);
	}

	@Override
	public Persona findById(Integer personaId) {
		Persona persona = em.find(Persona.class,personaId);
		return persona;
	}

	@Override
	public List<Persona> findByField(String field) {
		TypedQuery<Persona> query = em.createQuery("select x from Persona x where x.cognome = :cognome",Persona.class);
		List<Persona> persone = query.setParameter("cognome",field).getResultList();
		return persone;
	}

	public List<Persona> findByNomeAndCognome(String nome, String cognome) {
		TypedQuery<Persona> query = em.createQuery("select x from Persona x where x.nome = :nome AND x.cognome = :cognome",Persona.class);
		query =  query.setParameter("nome", nome);
		query =  query.setParameter("cognome",cognome);
		List<Persona> persone = query.getResultList();
		return persone;
	}
}
