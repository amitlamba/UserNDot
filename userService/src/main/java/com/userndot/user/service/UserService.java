package com.userndot.user.service;

import com.userndot.model.user.User;

public interface UserService {

	public boolean saveUserDetailsInMongo(User user);
	public User getUserDetailsFromMongo(String userID);
}
