package com.azienda.esercizioJpa.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.azienda.esercizioJpa.model.Banca;

public class BancaDao implements DaoInterface<Banca>{

	private EntityManager em = null;
	
	public BancaDao() {
		this(null);
	}
	
	public BancaDao (EntityManager em) {
		setManager(em);
	}

	public EntityManager getManager() {
		return em;
	}

	public void setManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void create(Banca banca) {
		em.persist(banca);
	}

	@Override
	public List<Banca> retrieve() {
		List<Banca> banche = em.createQuery("from Banca",Banca.class).getResultList();
		return banche;
	}

	@Override
	public void update(Banca banca) {
		em.persist(banca);
	}

	@Override
	public void delete(Banca banca) {
		em.remove(banca);
	}
	
	@Override
	public Banca findById(Integer bancaId) {
		Banca banca = em.find(Banca.class,bancaId);
		return banca;
	}
	
	@Override
	public List<Banca> findByField(String field) {
		TypedQuery<Banca> query = em.createQuery("select x from Banca x where x.nome = :nome",Banca.class);
		List<Banca> banche = query.setParameter("nome",field).getResultList();
		return banche;
	}
	
}
