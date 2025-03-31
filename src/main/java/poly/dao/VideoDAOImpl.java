package poly.dao;

import java.util.List;

import poly.entity.Video;
import poly.service.XJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VideoDAOImpl implements VideoDAO {
	EntityManager em = XJPA.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
	}

	@Override
	public List<Video> findAll() {
		String jpql = "SELECT o FROM Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(String id) {
		return em.find(Video.class, id);
	}

	@Override
	public void create(Video entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Video entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void deleteById(String id) {
		Video entity = em.find(Video.class, id);
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public List<Object[]> findAllWithLikeCount() {
		String jpql = "SELECT v.title, COUNT(f) AS likeCount, v.active "
				+ "FROM Video v LEFT JOIN Favorite f ON v.id = f.video.id " + "GROUP BY v.id, v.title, v.active";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		return query.getResultList();
	}

	@Override
	public List<Object[]> findByTitleWithLikeCount(String title) {
		String jpql = "SELECT v.title, COUNT(f) AS likeCount, v.active "
				+ "FROM Video v LEFT JOIN Favorite f ON v.id = f.video.id " + "WHERE v.title LIKE :title "
				+ "GROUP BY v.id, v.title, v.active";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		query.setParameter("title", "%" + title + "%"); // Tìm kiếm với tiêu đề có chứa từ khóa
		return query.getResultList();
	}
}
