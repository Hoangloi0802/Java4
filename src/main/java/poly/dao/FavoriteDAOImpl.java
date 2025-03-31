package poly.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.entity.Favorite;
import poly.entity.Video;
import poly.service.XJPA;

public class FavoriteDAOImpl implements FavoriteDAO {
    private EntityManager em = XJPA.getEntityManager();

    @Override
    public List<Favorite> findAll() {
        String jpql = "SELECT f FROM Favorite f";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        return query.getResultList();
    }

    @Override
    public Favorite findById(String id) {
        return em.find(Favorite.class, id);
    }

    @Override
    public void create(Favorite entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error while creating Favorite entity: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Favorite entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error while updating Favorite entity: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(String id) {
        Favorite entity = em.find(Favorite.class, id);
        if (entity == null) {
            throw new RuntimeException("Favorite entity with ID " + id + " not found.");
        }
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error while deleting Favorite entity: " + e.getMessage(), e);
        }
    }

    @Override
    public Video findVideoById(String videoId) {
        try {
            Video video = em.find(Video.class, videoId);
            if (video == null) {
                throw new RuntimeException("Video entity with ID " + videoId + " not found.");
            }
            return video;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while retrieving Video entity: " + e.getMessage(), e);
        }
    }
    @Override
    public void deleteFavorite(String userId, String videoId) {
        try {
            // Truy vấn để tìm bản ghi yêu thích
            String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
            TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class)
                                           .setParameter("userId", userId)
                                           .setParameter("videoId", videoId);
            Favorite favorite = query.getSingleResult();

            // Xóa bản ghi yêu thích nếu tồn tại
            em.getTransaction().begin();
            em.remove(favorite);
            em.getTransaction().commit();
        } catch (jakarta.persistence.NoResultException e) {
            throw new RuntimeException("Favorite record not found for userId: " + userId + " and videoId: " + videoId);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error while deleting favorite: " + e.getMessage(), e);
        }
    }
    @Override
    public List<Video> findFavoritesByUser(String userId) {
        String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    public List<Video> findLikedVideosByUser(String userId) {
        String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
}
