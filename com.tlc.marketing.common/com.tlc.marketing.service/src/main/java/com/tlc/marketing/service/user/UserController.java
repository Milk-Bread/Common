package com.tlc.marketing.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tlc.marketing.business.user.UserService;
import com.tlc.marketing.domain.user.User;

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

  @RequestMapping(value = "Login.do")
  @ResponseBody
  public Object jsontest(HttpServletRequest request, String test) {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("aaa", "adsf");
    response.put("bbb", "来个中文试试");
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    list.add(response);
    return list;
  }
}
