package com.tlc.marketing.utils;

/**
 * Description: 微信调用接口名称定义
 * Copyright (c) TLC.
 * All Rights Reserved.
 *
 * @version 1.0 2016年10月8日 下午8:25:41 by chepeiqing (chepeiqing@icloud.com)
 */
public class WeChat {
    /**
     * 获取access_token号
     **/
    public static String TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 创建二维码ticket（永久/临时）
     **/
    public static String CREAT_QRCODE_IMAGE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    /**
     * 生成关注二维码
     **/
    public static String SHOW_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
    /**
     * 分类型获取永久素材的列表 POST
     **/
    public static String BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
    /**
     * 获取永久素材的列表 GET
     **/
    public static String GET_MATERIALCOUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";


}
