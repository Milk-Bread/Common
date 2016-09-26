package com.tlc.marketing.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tlc.marketing.business.menu.MenuService;
import com.tlc.marketing.business.user.UserService;
import com.tlc.marketing.domain.Menu;
import com.tlc.marketing.domain.TlcUser;
import com.tlc.marketing.utils.CHECKMSG;
import com.tlc.marketing.utils.EncodeUtil;

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
    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/")
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    /**
     * Description: 用户登陆
     * @Version1.0 2016年8月1日 下午3:50:11 by chepeiqing (chepeiqing@icloud.com)
     * @param request
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value = "Login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, String userId, String password) {
        String passwordaes = EncodeUtil.aesEncrypt(password);
        TlcUser user = userService.loginCheck(userId, passwordaes);
        if (user == null) {
            throw new RuntimeException(CHECKMSG.USER_DOES_NOT_EXIST);
        }
        // 创建session
        request.getSession(true);
        // 将user对象存入session
        request.getSession().setAttribute("_USER", user);
        return user;
    }


    /**
     * Description: 根据用户角色加载菜单
     * @Version1.0 2016年8月1日 下午3:49:50 by chepeiqing (chepeiqing@icloud.com)
     * @param request
     * @return
     */
    @RequestMapping(value = "LodeMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object lodeMenu(HttpServletRequest request) {
        TlcUser user = (TlcUser) request.getSession().getAttribute("_USER");
        String parentId = "00000000";
        List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
        // 查询主菜单
        List<Menu> zuMenu = menuService.getMenu(parentId, user.getRoleSeq());
        for (Menu menu : zuMenu) {
            Map<String, Object> menuMap = new HashMap<String, Object>();
            menuMap.put("menuOne", menu.getMenuName());
            parentId = menu.getMenuId();
            // 查子菜单
            List<Menu> ziMenu = menuService.getMenu(parentId, user.getRoleSeq());
            List<Map<String, Object>> menu2List = new ArrayList<Map<String, Object>>();
            for (Menu menu2 : ziMenu) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", menu2.getMenuName());
                map.put("url", menu2.getTransId());
                menu2List.add(map);
            }
            menuMap.put("menuTwo", menu2List);
            menuList.add(menuMap);
        }
        return menuList;
    }
}
