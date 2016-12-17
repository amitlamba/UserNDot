package com.userndot.email.service;

import com.userndot.model.user.User;

public interface EmailService {

	public boolean saveUserDetailsInMongo(User user);
	public User getUserDetailsFromMongo(String userID);
}
