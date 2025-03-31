package poly.dao;

import java.util.List;
import poly.entity.Favorite;
import poly.entity.Video;

public interface FavoriteDAO {
  
    List<Favorite> findAll();

    
    Favorite findById(String favoriteId);

    void create(Favorite item);

   
    void update(Favorite item);

    void deleteById(String favoriteId);

    void deleteFavorite(String userId, String videoId);
    List<Video> findFavoritesByUser(String userId);
    Video findVideoById(String videoId);


	List<Video> findLikedVideosByUser(String id);
}
