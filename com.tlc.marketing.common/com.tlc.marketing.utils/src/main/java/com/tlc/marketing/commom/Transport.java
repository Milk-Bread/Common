package com.tlc.marketing.commom;

import com.tlc.marketing.service.Service;

/**
 * Description: 请求Transport公共父类
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年10月2日 上午10:53:43 by chepeiqing (chepeiqing@icloud.com)
 */
public abstract interface Transport extends Service {
    public abstract Object submit(Object sendParam) throws Exception;
}
