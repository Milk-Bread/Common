package com.tlc.marketing.dao.user;

import com.tlc.marketing.domain.user.User;


public interface UserDao {
	 
    /**
     * @param userId
     * @return User
     */
    public User selectUserById(Integer userId);  
 
}
