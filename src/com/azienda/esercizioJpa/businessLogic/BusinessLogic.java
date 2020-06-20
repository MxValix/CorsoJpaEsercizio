package com.azienda.esercizioJpa.businessLogic;

import java.util.List;

import com.azienda.esercizioJpa.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BusinessLogic {

	private PersonaDao personaDao = null;
	private ContoCorrenteDao contoDao = null;
	private BancaDao bancaDao = null;
	private EntityManager em = null;

	
	public BusinessLogic(EntityManager em, PersonaDao personaDao ,BancaDao bancaDao,  ContoCorrenteDao contoDao) {
		setEntityManager(em);
		setPersonaDao(personaDao);
		setBancaDao(bancaDao);
		setContoDao(contoDao);
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public PersonaDao getPersonaDao() {
		return personaDao;
	}

	public void setPersonaDao(PersonaDao personaDao) {
		this.personaDao = personaDao;
	}

	public ContoCorrenteDao getContoDao() {
		return contoDao;
	}

	public void setContoDao(ContoCorrenteDao contoDao) {
		this.contoDao = contoDao;
	}

	public BancaDao getBancaDao() {
		return bancaDao;
	}

	public void setBancaDao(BancaDao bancaDao) {
		this.bancaDao = bancaDao;
	}

	public void create(Persona persona, List<ContoCorrente> conti, Banca banca) {
		EntityTransaction emTrans = em.getTransaction();
		emTrans.begin();
		try {
			bancaDao.create(banca);
			personaDao.create(persona);
			for(int i=0; i<conti.size();i++) {
				ContoCorrente conto = conti.get(i);
				banca.getConti().add(conto);
				conto.setBanca(banca);
				conto.getPersone().add(persona);
				contoDao.create(conto);
			}
			emTrans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			emTrans.rollback();
		}
	}

	public void spostaFondi(String nome1, String cognome1, String nome2, String cognome2, Float importo) {
		EntityTransaction emTrans = em.getTransaction();
		emTrans.begin();
		try {
			Persona persona1 = personaDao.findByNomeAndCognome(nome1, cognome1).get(0);
			Persona persona2 = personaDao.findByNomeAndCognome(nome2, cognome2).get(0);
			boolean checkCondition = persona1!=null && persona2!=null;
			if(checkCondition) {
				setSaldoConto(persona1, importo, 1);
				setSaldoConto(persona2,importo, 2);	
			}
			emTrans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			emTrans.rollback();
		}
	}


	public void setSaldoConto(Persona persona, Float importo, int operation) {
		Integer numeroConti = persona.getConti().size();
		ContoCorrente conto = null;
		for (int i=0; i<numeroConti; i++) {
			conto = persona.getConti().get(i);
			Float nuovoSaldo = conto.getSaldo();
			if (operation==1) {
				nuovoSaldo = nuovoSaldo - importo;
			}
			else if (operation==2) {
				nuovoSaldo = nuovoSaldo + importo;
			}
			conto.setSaldo(nuovoSaldo);
			contoDao.update(conto);
		}
	}

}
