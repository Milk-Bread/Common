package com.tlc.marketing.httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tlc.marketing.commom.Transport;
import com.tlc.marketing.utils.Constants;
import com.tlc.marketing.utils.Dict;

public class HttpClientTransport implements Transport {
    private static Logger logger = LoggerFactory.getLogger(HttpClientTransport.class);
    /** 读取超时时间 **/
    private Integer readTimeout;
    /** 连接超时时间 **/
    private Integer connectTimeout;

    @Override
    public Object submit(Object sendParam, String method) throws Exception {
        Map<String, Object> sendMap = (Map<String, Object>) sendParam;
        if (method.equalsIgnoreCase("POST")) {
            return sendPost(sendMap.get(Dict.TRANS_NAME).toString(), sendParam);
        } else {
            return sendGet(sendMap.get(Dict.TRANS_NAME).toString(), sendParam);
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url
     *        发送请求的URL
     * @param param
     *        请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public Object sendGet(String url, Object param) {
        String result = "";
        BufferedReader in = null;
        try {
            logger.debug("Get请求");
            String urlNameString = url + "?" + getUrlParamsByMap((Map) param);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setConnectTimeout(connectTimeout);// 连接超时30秒
            connection.setReadTimeout(readTimeout);// 读取超时30秒
            // connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                logger.debug(key + "===>" + map.get(key));
            }
            if ("image/jpg".equals(connection.getContentType())) {
                InputStream inputStream = connection.getInputStream();
                try {
                    byte[] data = new byte[1024];
                    int len = 0;
                    FileOutputStream fileOutputStream = null;
                    fileOutputStream = new FileOutputStream(Constants.PATH_QRCODE_IMAGE + "/" + ((Map) param).get("Name") + ".jpg");
                    while ((len = inputStream.read(data)) != -1) {
                        fileOutputStream.write(data, 0, len);

                    }
                } catch (IOException e) {
                    logger.debug("图片写入失败", e);
                } finally {
                    if (inputStream != null) inputStream.close();
                }
                return null;
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.debug("发送GET请求出现异常！", e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        logger.debug(result);
        return getResponseParam(result);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url
     *        发送请求的 URL
     * @param param
     *        请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public Object sendPost(String url, Object param) {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            logger.debug("Post请求");
            URL realUrl = new URL(url + "?" + Dict.ACCESS_TOKEN + "=" + ((Map) param).get(Dict.ACCESS_TOKEN));
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            // connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setConnectTimeout(connectTimeout);// 连接超时30秒
            connection.setReadTimeout(readTimeout);// 读取超时30秒
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            // 获取URLConnection对象对应的输出流
            out = new DataOutputStream(connection.getOutputStream());
            // 发送请求参数
            out.writeBytes(getMapByJson((Map) param).toString());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.debug("发送 POST 请求出现异常！", e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return getResponseParam(result);
    }

    public static Object getResponseParam(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("http message:===>" + message);
            Map<String, Object> responseMap = mapper.readValue(message, HashMap.class);
            logger.debug("http response:===>" + responseMap);
            return responseMap;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (Dict.TRANS_NAME.equals(entry.getKey())) {
                continue;
            }
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.lastIndexOf("&"));
        }
        logger.debug("http request:===>" + s);
        return s;
    }

    public static JSONObject getMapByJson(Map<String, Object> map) {
        JSONObject json = JSONObject.fromObject(map);
        logger.debug("http request:===>" + json);
        return json;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

}
