package com.tlc.marketing.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tlc.marketing.dao.user.UserDao;
import com.tlc.marketing.domain.user.User;

@Service 
public class UserServiceImpl implements UserService {
 
    @Autowired 
    private UserDao userDao;  
 
    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  
 
    }
}
