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
import com.tlc.marketing.business.wechat.WeChatService;
import com.tlc.marketing.commom.Transport;
import com.tlc.marketing.domain.CheckModel;
import com.tlc.marketing.utils.Constants;
import com.tlc.marketing.utils.Dict;
import com.tlc.marketing.utils.WeChat;


@Controller
public class WeixinController {

    @Autowired
    private TokenService tokenService;
    private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
    @Resource(name = "httpTransport")
    private Transport transport;
    @Resource
    private WeChatService weChatService;

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
        return tokenService.validate(Constants.WXTOKEN, tokenModel);
    }

    @RequestMapping(value = "getAccessToken", method = RequestMethod.GET)
    @ResponseBody
    public String getAccessToken() {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.GRANT_TYPE, Constants.CLIENT_CREDENTIAL);
        sendParam.put(Dict.APPID, Constants.WXTOKEN);
        sendParam.put(Dict.SECRET, Constants.APPSECRET);
        sendParam.put(Dict.TRANS_NAME, WeChat.TOKEN);
        String accessToken = "";
        try {
            Map<String, Object> access = weChatService.qAccessToken();
            logger.debug(access.toString());
            if (false == (Boolean) access.get("effective")) {
                Map<String, Object> resp = (Map<String, Object>) transport.submit(sendParam);
                sendParam = new HashMap<String, Object>();
                sendParam.put("accessToken", resp.get("access_token"));
                sendParam.put("invalidTime", resp.get("expires_in"));
                accessToken = (String) resp.get("access_token");
                weChatService.dAccessToken();
                weChatService.iAccessToken(sendParam);
            } else {
                accessToken = (String) access.get("accessToken");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
