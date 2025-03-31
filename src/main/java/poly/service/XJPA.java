 package poly.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("ASS_PD10302");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
