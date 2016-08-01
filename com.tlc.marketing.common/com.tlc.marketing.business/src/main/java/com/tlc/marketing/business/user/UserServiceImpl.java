package com.tlc.marketing.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlc.marketing.dao.UserDao;
import com.tlc.marketing.domain.TlcUser;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public TlcUser loginCheck(String userId, String password) {
    TlcUser user = new TlcUser();
    user.setUserId(userId);
    user.setPassword(password);
    return userDao.loginCheck(user);
  }
}
