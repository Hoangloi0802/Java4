package poly.dao;

import java.util.List;

import poly.entity.Share;
import poly.entity.Video;
import poly.service.XJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ShareDAOImpl implements ShareDAO {

	EntityManager em = XJPA.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
	}

	@Override
	public List<Share> findAll() {
		String jpql = "SELECT s FROM Share s";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		return query.getResultList();
	}

	@Override
	public Share findById(String id) {
		return em.find(Share.class, id);
	}

	@Override
	public void create(Share share) {
		try {
			em.getTransaction().begin();
			em.persist(share);
			em.getTransaction().commit();
			System.out.println("Add video successfull!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Error");
		}
	}

	@Override
	public void update(Share share) {
		try {
			em.getTransaction().begin();
			em.merge(share);
			em.getTransaction().commit();
			System.out.println("Update video successfull!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error");
		}
	}

	@Override
	public void deleteById(String id) {
		Share entity = em.find(Share.class, id);
		if (entity != null) {
			try {
				em.getTransaction().begin();
				em.remove(entity);
				em.getTransaction().commit();
				System.out.println("Video with ID " + id + " delete.");
			} catch (Exception e) {
				em.getTransaction().rollback();
			}
		} else {
			System.out.println("Video with ID " + id + " not found.");
		}
	}
	@Override
	public List<Object[]> findVideoShareStatistics() {
		String jpql = "SELECT v.title, COUNT(s.video.id) AS shareCount, " +
                "MIN(s.shareDate) AS firstShareDate, MAX(s.shareDate) AS lastShareDate " +
                "FROM Video v LEFT JOIN Share s ON v.id = s.video.id " +
                "GROUP BY v.title";
	  TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	  return query.getResultList();
	}


}
