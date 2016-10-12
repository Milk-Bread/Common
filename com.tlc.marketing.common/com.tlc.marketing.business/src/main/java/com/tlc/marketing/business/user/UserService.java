package com.tlc.marketing.business.user;

import com.tlc.marketing.domain.TlcUser;

import java.util.List;
import java.util.Map;

public interface UserService {
  TlcUser loginCheck(String userId, String password);
  /**
   * Description:角色查询
   * @Version1.0 2016-07-25 10:39:42 by chepeiqing (chepeiqing@icloud.com)
   * @param roleName
   * @return
   */
  public List<Map<String, Object>> roleQuery(String roleName);
}
