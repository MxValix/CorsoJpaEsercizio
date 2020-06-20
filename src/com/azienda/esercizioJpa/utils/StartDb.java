package com.azienda.esercizioJpa.utils;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.azienda.esercizioJpa.businessLogic.BancaDao;
import com.azienda.esercizioJpa.businessLogic.BusinessLogic;
import com.azienda.esercizioJpa.businessLogic.ContoCorrenteDao;
import com.azienda.esercizioJpa.businessLogic.PersonaDao;

import com.azienda.esercizioJpa.model.Banca;
import com.azienda.esercizioJpa.model.ContoCorrente;
import com.azienda.esercizioJpa.model.Persona;


public class StartDb {

	public static void main(String[] args) {
		EntityManager em = null;
		
		try {
			
			String nomeLogico = "CorsoJpaEsercizio";
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(nomeLogico);
			em = emf.createEntityManager();
			
			BancaDao bancaDao = new BancaDao(em);
			ContoCorrenteDao contoDao = new ContoCorrenteDao(em);
			PersonaDao personaDao = new PersonaDao(em);
			
			BusinessLogic businessLogic = new BusinessLogic(em,personaDao,bancaDao,contoDao);
			
			List<Persona> personeConto1 = new ArrayList<Persona>();
			List<Persona> personeConto2 = new ArrayList<Persona>();
			List<ContoCorrente> contiPersona1 = new ArrayList<ContoCorrente>();
			List<ContoCorrente> contiPersona2 = new ArrayList<ContoCorrente>();
			
			Persona persona1 = setContoPersona("Mario", "Bianchi", 1200F, personeConto1, contiPersona1);
			Persona persona2 = setContoPersona("Paolo", "Rossi", 100F, personeConto2, contiPersona2);
			
			List<ContoCorrente> conti = new ArrayList<ContoCorrente>(contiPersona1);
			conti.addAll(contiPersona2);
			
			String nomeBanca = "Intesa San Paolo";
			Banca banca1 = new Banca(nomeBanca, conti);
			
			
			businessLogic.create(persona1, contiPersona1, banca1);
			businessLogic.create(persona2, contiPersona2, banca1);
			
			//spostaFondi
			
			String nomePersona1 = persona1.getNome();
			String cognomePersona1 = persona1.getCognome();
			String nomePersona2 = persona2.getNome();
			String cognomePersona2 = persona2.getCognome();
			Float importo = 1000F;
			
			businessLogic.spostaFondi(nomePersona1,cognomePersona1,nomePersona2,cognomePersona2,importo);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
			System.exit(0);
		}
	}

	private static Persona setContoPersona(String nome, String cognome, Float saldo, List<Persona> personeConto, List<ContoCorrente> contiPersona) {
		Persona persona = new Persona(nome, cognome, null);
		personeConto.add(persona);
		String numeroConto = Long.toHexString(Double.doubleToLongBits(Math.random()));
		ContoCorrente contoCorrente1 = new ContoCorrente (numeroConto, saldo, personeConto);
		numeroConto = Long.toHexString(Double.doubleToLongBits(Math.random()));
		ContoCorrente contoCorrente2 = new ContoCorrente (numeroConto, saldo, personeConto);
		contiPersona.add(contoCorrente1);
		contiPersona.add(contoCorrente2);
		persona.setConti(contiPersona);
		return persona;
	}
	
	

}