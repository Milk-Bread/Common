package com.tlc.marketing.business.menu;

import java.util.List;

import com.tlc.marketing.domain.Menu;

public interface MenuService {
  List<Menu> getMenu(String parentId, Integer roleSeq);
}
