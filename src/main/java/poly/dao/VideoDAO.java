package poly.dao;

import java.util.List;

import poly.entity.Video;

public interface VideoDAO {
	List<Video> findAll();

	/** Truy vấn theo mã */
	Video findById(String id);

	/** Thêm mới */
	void create(Video item);

	/** Cập nhật */
	void update(Video item);

	/** Xóa theo mã */
	void deleteById(String id);
	List<Object[]> findAllWithLikeCount();

	List<Object[]> findByTitleWithLikeCount(String title);
}
