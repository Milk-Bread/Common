package com.tlc.marketing.business.user;

import com.tlc.marketing.domain.user.User;

public interface UserService {
  User selectUserById(Integer userId);

  User loginCheck(String userName, String password);
}
