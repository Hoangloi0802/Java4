package poly.dao;

import java.util.List;

import poly.controller.EmailUntil;
import poly.entity.Users;
import poly.service.XJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAOImpl implements UserDAO {
	  private EntityManager em;

	    public UserDAOImpl() {
	        this.em = XJPA.getEntityManager();
	    }

	    // Giải phóng tài nguyên khi đối tượng không còn được sử dụng
	    public void close() {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }

	    @Override
	    public List<Users> findAll() {
	        String jpql = "SELECT u FROM Users u";
	        TypedQuery<Users> query = em.createQuery(jpql, Users.class);
	        return query.getResultList();
	    }

	    @Override
	    public Users findByIdOrEmail(String idOrEmail) {
	        try {
	            TypedQuery<Users> query = em.createQuery(
	                "SELECT u FROM Users u WHERE u.id = :idOrEmail OR u.email = :idOrEmail", Users.class);
	            query.setParameter("idOrEmail", idOrEmail);
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;  // Không tìm thấy user
	        }
	    }
	    public List<Object[]> findAllWithDetails() {
	        String jpql = "SELECT v.title, COUNT(f), v.isActive " +
	                      "FROM Video v LEFT JOIN v.favorites f " +
	                      "GROUP BY v.id, v.title, v.isActive";

	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        return query.getResultList();
	    }

	    @Override
	    public Users findById(String id) {
	        return em.find(Users.class, id);
	    }

	    @Override
	    public void create(Users entity) {
	        try {
	            em.getTransaction().begin();
	            em.persist(entity);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void update(Users entity) {
	        try {
	            em.getTransaction().begin();
	            em.merge(entity);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteById(String id) {
	        Users entity = em.find(Users.class, id);
	        if (entity != null) {
	            try {
	                em.getTransaction().begin();
	                em.remove(entity);
	                em.getTransaction().commit();
	            } catch (Exception e) {
	                if (em.getTransaction().isActive()) {
	                    em.getTransaction().rollback();
	                }
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Users with id " + id + " not found.");
	        }
	    }
	    public boolean sendEmailToUser(String userId, String subject, String body) {
			Users user = findById(userId);
			if (user != null) {
				String email = user.getEmail();
				return EmailUntil.sendEmail(email, subject, body);
			}
			return false;
		}
}
