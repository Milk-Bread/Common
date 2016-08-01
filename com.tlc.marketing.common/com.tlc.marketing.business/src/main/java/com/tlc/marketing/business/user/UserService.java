package com.tlc.marketing.business.user;

import com.tlc.marketing.domain.TlcUser;

public interface UserService {
  TlcUser loginCheck(String userId, String password);
}
