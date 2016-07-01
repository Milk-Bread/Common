package com.tlc.marketing.dao.user;

import com.tlc.marketing.domain.user.User;


public interface UserDao {

  /**
   * @param userId
   * @return User
   */
  public User selectUserById(Integer userId);

  /**
   * Description:登陆校验
   * @Version1.0 2016年7月1日 上午11:33:25 by chepeiqing (chepeiqing@icloud.com)
   * @param user
   * @return
   */
  public User loginCheck(User user);

}
