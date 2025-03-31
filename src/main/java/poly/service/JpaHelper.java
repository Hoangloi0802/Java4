package poly.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaHelper {
	private static EntityManager em = null;
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ASS_PD10302");
	public static EntityManager instance() {
		if(em == null) {
			em = factory.createEntityManager();
		}
		return em;
	}
	public static void close() {
		em.close();
	}
}