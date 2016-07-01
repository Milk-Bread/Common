package com.tlc.marketing.service.core;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlc.marketing.business.core.TokenService;
import com.tlc.marketing.domain.core.CheckModel;


@Controller
public class WeixinController {

  @Autowired
  private TokenService tokenService;

  /**
   * 开发者模式token校验
   * @param wxAccount 开发者url后缀
   * @param response
   * @param tokenModel
   * @throws ParseException
   * @throws IOException
   */
  @RequestMapping(value = "wechat.do", method = RequestMethod.GET)
  @ResponseBody
  public String validate(String wxToken, CheckModel tokenModel) throws ParseException, IOException {
    System.out.println("asf");
    return tokenService.validate(wxToken, tokenModel);
  }

}
