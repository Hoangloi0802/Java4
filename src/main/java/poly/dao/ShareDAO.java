package poly.dao;

import java.util.List;

import poly.entity.Share;

public interface ShareDAO {
	List<Share> findAll();

	Share findById(String id);

	void create(Share share);

	void update(Share share);

	void deleteById(String id);

	List<Object[]> findVideoShareStatistics();

}
