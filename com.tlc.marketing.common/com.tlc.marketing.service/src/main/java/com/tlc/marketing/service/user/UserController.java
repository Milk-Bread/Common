package com.tlc.marketing.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tlc.marketing.business.user.UserService;
import com.tlc.marketing.domain.user.User;
import com.tlc.marketing.utils.CHECKMSG;

/**
 * Description: 核心控制器
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年6月27日 下午3:27:05 by chepeiqing (chepeiqing@icloud.com)
 */
@Controller
public class UserController {

  @Resource
  private UserService userService;

  @RequestMapping(value = "/")
  public ModelAndView getIndex() {
    ModelAndView mav = new ModelAndView("index");
    User user = userService.selectUserById(1);
    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping(value = "Login.do", method = RequestMethod.POST)
  @ResponseBody
  public Object login(String userName, String password) {
    User user = userService.loginCheck(userName, password);
    if (user == null) {
      throw new RuntimeException(CHECKMSG.USER_DOES_NOT_EXIST);
    }
    return user;
  }
}
