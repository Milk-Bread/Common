package com.tlc.marketing.service.core;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tlc.marketing.business.core.TokenService;
import com.tlc.marketing.commom.Transport;
import com.tlc.marketing.domain.CheckModel;
import com.tlc.marketing.utils.Dict;


@Controller
public class WeixinController {

    @Autowired
    private TokenService tokenService;
    private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
    @Resource(name = "httpTransport")
    private Transport transport;

    /**
     * 开发者模式token校验
     * @param wxAccount 开发者url后缀
     * @param response
     * @param tokenModel
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "wechat", method = RequestMethod.GET)
    @ResponseBody
    public String validate(CheckModel tokenModel) throws ParseException, IOException {
        logger.debug("微信token校验" + tokenModel.toString());
        return tokenService.validate(Dict.WXTOKEN, tokenModel);
    }

    @RequestMapping(value = "getAccessToken", method = RequestMethod.GET)
    @ResponseBody
    public String getAccessToken() {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put("grant_type", Dict.CLIENT_CREDENTIAL);
        sendParam.put("appid", Dict.WXTOKEN);
        sendParam.put("secret", Dict.APPSECRET);
        try {
            Map<String, Object> resp = (Map<String, Object>) transport.submit(sendParam);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
