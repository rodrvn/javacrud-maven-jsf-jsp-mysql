package com.ecodeup.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	
	private static EntityManagerFactory factory;
// Si nuestro objeto factory es null crea la entidad que conecta a la base de datos
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory==null) {
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;				
	}
// Opcion para desconectarnos a la base de datos
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
		}
	}

}
