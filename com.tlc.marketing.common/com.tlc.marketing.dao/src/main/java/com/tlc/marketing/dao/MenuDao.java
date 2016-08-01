package com.tlc.marketing.dao;

import java.util.List;
import java.util.Map;

import com.tlc.marketing.domain.Menu;

public interface MenuDao {

  /**
   * Description:加载菜单
   * @Version1.0 2016年8月1日 下午2:24:38 by chepeiqing (chepeiqing@icloud.com)
   * @return
   */
  public List<Menu> getMenu(Map<String, Object> map);
}
