package com.tlc.marketing.dao;

import com.tlc.marketing.domain.TlcUser;

import java.util.Map;


public interface UserDao {

  /**
   * Description:登陆校验
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param user
   * @return
   */
  public TlcUser loginCheck(TlcUser user);

  /**
   * Description:登陆校验
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param roleName
   * @return
   */
  public Map<String, Object> roleQuery(String roleName);

}
