package com.tlc.marketing.business.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlc.marketing.dao.UserDao;
import com.tlc.marketing.domain.TlcUser;

import java.util.Map;

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

  /**
   * Description:角色查询
   *
   * @param roleName
   * @return
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   */
  @Override
  public Map<String, Object> roleQuery(String roleName) {
    return userDao.roleQuery(roleName);
  }
}
