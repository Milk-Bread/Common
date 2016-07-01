package com.tlc.marketing.web.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tlc.marketing.business.user.UserService;
import com.tlc.marketing.domain.user.User;
import com.tlc.marketing.web.basetest.SpringTestCase;

public class UserServiceTest extends SpringTestCase {
	 
    @Autowired 
    private UserService userService; 
 
    @Test 
    public void selectUserByIdTest(){  
        User user = userService.selectUserById(1);  
        System.out.println(user.getUserName() + ":" + user.getUserPassword());
    }  
}