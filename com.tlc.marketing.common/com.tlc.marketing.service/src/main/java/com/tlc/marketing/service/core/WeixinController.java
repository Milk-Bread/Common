package com.tlc.marketing.service.core;

import com.tlc.marketing.business.core.TokenService;
import com.tlc.marketing.business.core.Transformer;
import com.tlc.marketing.business.wechat.WeChatService;
import com.tlc.marketing.commom.Transport;
import com.tlc.marketing.domain.CheckModel;
import com.tlc.marketing.utils.Constants;
import com.tlc.marketing.utils.Dict;
import com.tlc.marketing.utils.Util;
import com.tlc.marketing.utils.WeChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class WeixinController {

    @Autowired
    private TokenService tokenService;
    private static Logger logger = LoggerFactory.getLogger(WeixinController.class);
    @Resource(name = "httpTransport")
    private Transport transport;
    @Resource
    private WeChatService weChatService;
    @Autowired
    private Transformer transformer;

    /**
     * 开发者模式token校验
     *
     * @param wxAccount  开发者url后缀
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

    /**
     * Description: 接收微信推送的消息
     *
     * @return
     * @throws ParseException
     * @throws IOException
     * @Version1.0 2016年10月9日 下午4:37:49 by chepeiqing (chepeiqing@icloud.com)
     */
    @RequestMapping(value = "wechat", method = RequestMethod.POST)
    @ResponseBody
    public void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        Map<String, Object> map = transformer.parse(request);
        Map<String, Object> msgMap = weChatService.msgType(map);
        String respXml = transformer.former(msgMap);
        response.getWriter().write(respXml);
    }


    @RequestMapping(value = "getAccessToken", method = RequestMethod.GET)
    @ResponseBody
    public String getAccessToken() {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        sendParam.put(Dict.GRANT_TYPE, Constants.CLIENT_CREDENTIAL);
        sendParam.put(Dict.APPID, Constants.APPID);
        sendParam.put(Dict.SECRET, Constants.APPSECRET);
        sendParam.put(Dict.TRANS_NAME, WeChat.TOKEN);
        String accessToken = "";
        try {
            Map<String, Object> access = weChatService.qAccessToken();
            if (access == null || false == (Boolean) access.get("effective")) {
                Map<String, Object> resp = (Map<String, Object>) transport.submit(sendParam, "Get");
                sendParam = new HashMap<String, Object>();
                sendParam.put("accessToken", resp.get("access_token"));
                sendParam.put("invalidTime", resp.get("expires_in"));
                accessToken = (String) resp.get("access_token");
                if (accessToken != null) {
                    weChatService.dAccessToken();
                    weChatService.iAccessToken(sendParam);
                }
            } else {
                accessToken = (String) access.get("accessToken");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    @RequestMapping(value = "createQrcodeImg", method = RequestMethod.GET)
    @ResponseBody
    public void creatQrcodeImage() throws Exception {
        Map<String, Object> sendParam = new HashMap<String, Object>();
        for (int i = 0; i < 10; i++) {
            String id = "8" + Util.getCurrentTime() + i;
            // 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
            sendParam.put("action_name", "QR_LIMIT_SCENE");
            Map<String, Object> action_info = new HashMap<>();
            Map<String, Object> scene = new HashMap<>();
            scene.put("scene_str", id);
            action_info.put("scene", scene);
            sendParam.put("action_info", action_info);
            sendParam.put(Dict.TRANS_NAME, WeChat.CREAT_QRCODE_IMAGE);
            sendParam.put(Dict.ACCESS_TOKEN, getAccessToken());
            // 生成二维码ticket
            Map<String, Object> respTicket = (Map<String, Object>) transport.submit(sendParam, "POST");
            sendParam = new HashMap<>();
            sendParam.put("ticket", respTicket.get("ticket"));
            sendParam.put("Name", id);
            sendParam.put(Dict.TRANS_NAME, WeChat.SHOW_QRCODE);
            transport.submit(sendParam, "Get");
        }
    }
}
