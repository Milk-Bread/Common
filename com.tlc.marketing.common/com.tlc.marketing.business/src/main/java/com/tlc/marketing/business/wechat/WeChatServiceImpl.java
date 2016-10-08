package com.tlc.marketing.business.wechat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlc.marketing.dao.WeChantDao;

@Service
public class WeChatServiceImpl implements WeChatService {

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
}
