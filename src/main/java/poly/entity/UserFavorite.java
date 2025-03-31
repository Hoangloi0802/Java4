package poly.entity;

import java.io.Serializable;

public class UserFavorite implements Serializable {
	private String userId;
	private int count;
	
	public UserFavorite() {

	}
	
	public UserFavorite(String userId, int count) {
		this.userId = userId;
		this.count = count;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
