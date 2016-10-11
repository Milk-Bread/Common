package com.tlc.marketing.business.wechat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tlc.marketing.domain.wechat.Event;
import com.tlc.marketing.domain.wechat.MsgType;
import com.tlc.marketing.utils.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlc.marketing.dao.WeChantDao;

@Service
public class WeChatServiceImpl implements WeChatService {
    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    @Autowired
    public WeChantDao weChatDao;

    @Override
    public void iAccessToken(Map<String, Object> map) {
        weChatDao.iAccessToken(map);
    }

    @Override
    public void dAccessToken() {
        weChatDao.dAccessToken();
    }

    @Override
    public Map<String, Object> qAccessToken() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> accessMap = weChatDao.qAccessToken();
        if (accessMap == null) {
            return null;
        }
        logger.debug(accessMap.toString());
        Timestamp createTime = (Timestamp) accessMap.get("createTime");
        String invalidTime = (String) accessMap.get("invalidTime");
        long createlong = df.parse(df.format(createTime)).getTime();
        long time = new Date().getTime() - createlong;
        long inbalidlong = Long.valueOf(invalidTime) * 1000 - 1000;
        if (inbalidlong > time) {
            accessMap.put("effective", true);
        } else {
            accessMap.put("effective", false);
        }
        return accessMap;
    }

    @Override
    public Map<String, Object> msgType(Map<String, Object> param) {
        logger.debug("Enter a message distribution......");
        String msgType = (String) param.get(Dict.MSGTYPE);
        Map<String, Object> msgMap = new HashMap<>();
        //微信消息为事件消息
        if (msgType.equals(MsgType.event.toString())) {
            logger.debug("Messages are distributed as event messages");
            String event = (String) param.get(Dict.EVENT);
            if(Event.subscribe.toString().equals(event)){//事件类型为未关注扫码
                logger.debug("事件类型为未关注扫码");
                msgTypeBySubscribe(msgMap,param);
            }else if(Event.unsubscribe.toString().equals(event)){
                logger.debug("事件类型为取消关注");

            }else if(Event.SCAN.toString().equals(event)){
                logger.debug("事件类型为扫码(已经关注)");

            }else if(Event.VIEW.toString().equals(event)){
                logger.debug("事件类型为点击菜单跳转链接时的事件推送");

            }else if(Event.CLICK.toString().equals(event)){
                logger.debug("事件类型为点击菜单拉取消息时的事件推送");

            }else if(Event.LOCATION.toString().equals(event)){
                logger.debug("事件类型为上报地理位置");

            }
        } else if (msgType.equals(MsgType.text.toString())) {//微信消息为文本消息
            logger.debug("消息类型为文本消息");
            msgTypeByText(msgMap,param);
        } else if (msgType.equals(MsgType.image.toString())) {//微信消息为图片消息
            logger.debug("消息类型为图片消息");

        } else if (msgType.equals(MsgType.link.toString())) {//微信消息为连接消息
            logger.debug("消息类型为连接消息");

        } else if (msgType.equals(MsgType.shortvideo.toString())) {//微信消息为小视频消息消息
            logger.debug("消息类型为小视频消息");

        } else if (msgType.equals(MsgType.location.toString())) {//微信消息为地理位置消息
            logger.debug("消息类型为地理位置消息");

        } else if (msgType.equals(MsgType.video.toString())) {//微信消息为视频消息消息
            logger.debug("消息类型为视频消息");

        } else if (msgType.equals(MsgType.voice.toString())) {//微信消息为语音消息消息
            logger.debug("消息类型为语音消息");

        }
        return msgMap;
    }

    /**
     * 消息类型为文本消息处理方法
     * @param msgMap
     * @param param
     */
    private void msgTypeByText(Map<String, Object> msgMap,Map<String, Object> param){
        msgMap.put("ToUserName",param.get("FromUserName"));
        msgMap.put("FromUserName",param.get("ToUserName"));
        msgMap.put("CreateTime",param.get("CreateTime"));
        msgMap.put("MsgType","text");
        msgMap.put("Content",param.get("Content"));
    }

    /**
     * 事件类型为未关注扫码
     * @param msgMap
     * @param param
     */
    private void msgTypeBySubscribe(Map<String, Object> msgMap,Map<String, Object> param){

    }
}
