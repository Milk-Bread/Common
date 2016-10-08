package com.tlc.marketing.business.core;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlc.marketing.domain.CheckModel;
import com.tlc.marketing.utils.CHECKMSG;
import com.tlc.marketing.utils.Util;

@Service
public class TokenService {


    private static Logger logger = LoggerFactory.getLogger(TokenService.class);

    /**
     * 微信开发者验证
     * @param wxAccount
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @Transactional
    public String validate(String wxToken, CheckModel tokenModel) {
        String signature = tokenModel.getSignature();
        Long timestamp = tokenModel.getTimestamp();
        Long nonce = tokenModel.getNonce();
        String echostr = tokenModel.getEchostr();
        if (signature != null && timestamp != null & nonce != null) {
            String[] str = {wxToken, timestamp + "", nonce + ""};
            Arrays.sort(str); // 字典序排序
            String bigStr = str[0] + str[1] + str[2];
            // SHA1加密
            String digest = Util.encode("SHA1", bigStr).toLowerCase();
            // 确认请求来至微信
            if (digest.equals(signature)) {
                // 最好此处将echostr存起来，以后每次校验消息来源都需要用到
                logger.debug("微信开发者校验成功，echostr：" + echostr);
                return echostr;
            }
        }
        throw new RuntimeException(CHECKMSG.WECHAT_DEVELOPER_VALIDATION_FAILED);
    }


}
