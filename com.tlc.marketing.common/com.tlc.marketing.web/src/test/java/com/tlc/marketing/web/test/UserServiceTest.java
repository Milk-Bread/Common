package com.tlc.marketing.web.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tlc.marketing.business.user.UserService;
import com.tlc.marketing.domain.TlcUser;
import com.tlc.marketing.web.basetest.SpringTestCase;

public class UserServiceTest extends SpringTestCase {

  @Autowired
  private UserService userService;

  @Test
  public void selectUserByIdTest() {
    TlcUser user = userService.loginCheck("chepeiqing", "a88888888");
    System.out.println(user.getUserName() + ":" + user.getPassword());
  }
}
