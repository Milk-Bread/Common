package com.tlc.marketing.dao;

import com.tlc.marketing.domain.TlcUser;

import java.util.List;
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
  public List<Map<String, Object>> roleQuery(String roleName);

  /**
   * 新增角色
   * @param map
   * @return
     */
  public Integer addRole(Map<String, Object> map);

  /**
   * 新增角色菜单关联
   * @param map
   */
  public void addUsermenurelate(Map<String, Object> map);
}
