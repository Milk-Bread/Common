package com.tlc.marketing.dao;

import com.tlc.marketing.domain.TlcUser;


public interface UserDao {

  /**
   * Description:登陆校验
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param user
   * @return
   */
  public TlcUser loginCheck(TlcUser user);

}