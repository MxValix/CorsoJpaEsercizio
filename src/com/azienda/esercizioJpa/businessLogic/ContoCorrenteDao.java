package com.azienda.esercizioJpa.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.azienda.esercizioJpa.model.ContoCorrente;

public class ContoCorrenteDao implements DaoInterface<ContoCorrente> {
private EntityManager em = null;
	
	public ContoCorrenteDao() {
		this(null);
	}
	
	public ContoCorrenteDao (EntityManager em) {
		setManager(em);
	}

	public EntityManager getManager() {
		return em;
	}

	public void setManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void create(ContoCorrente conto) {
		em.persist(conto);
	}

	@Override
	public List<ContoCorrente> retrieve() {
		List<ContoCorrente> conti = em.createQuery("from Banca",ContoCorrente.class).getResultList();
		return conti;
	}

	@Override
	public void update(ContoCorrente conto) {
		em.persist(conto);
	}

	@Override
	public void delete(ContoCorrente conto) {
		em.remove(conto);
	}
	
	@Override
	public ContoCorrente findById(Integer contoId) {
		ContoCorrente conto = em.find(ContoCorrente.class,contoId);
		return conto;
	}
	
	@Override
	public List<ContoCorrente> findByField(String field) {
		TypedQuery<ContoCorrente> query = em.createQuery("select x from ContoCorrente x where x.numeroconto = :numeroconto",ContoCorrente.class);
		List<ContoCorrente> conti = query.setParameter("numeroconto",field).getResultList();
		return conti;
	}
}
