package poly.dao;

import java.util.List;


import poly.entity.Users;

public interface UserDAO {
	List<Users> findAll();

	/** Truy vấn theo mã */
	Users findById(String id);
	Users findByIdOrEmail(String idOrEmail);
	/** Thêm mới */
	void create(Users item);

	/** Cập nhật */
	void update(Users item);

	/** Xóa theo mã */
	void deleteById(String id);
	List<Object[]> findAllWithDetails();
}	
