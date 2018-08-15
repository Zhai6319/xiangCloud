package com.xiang.base.core.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiang.cloud.common.util.TxtUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * http协议请求工具类
 * @author zhaijianchao
 */
public class HttpHolder {
    private static final String TAO_BAO_API = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    /**
     * 获取当前request请求对象
     * */
    public static HttpServletRequest getHttpRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取当前response响应对象
     * */
    public static HttpServletResponse getHttpResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 获取当前请求全路经，不包含参数
     * */
    public static String getRequestPath(){
        String requestUrl = getHttpRequest().getRequestURL().toString();
        return requestUrl;
    }

    /**
     * 获取当前请求参数，不包含路径
     * */
    public static String getUrlParam(){
        String query = getHttpRequest().getQueryString();
        return query;
    }

    @NotNull
    /**
     * 获取当前请求全路经+参数
     * */
    public static String getRequestPathAndParam(){
        String requestUrl = getRequestPath();
        String query = getUrlParam();
        StringBuffer url = new StringBuffer();
        url.append(requestUrl);
        url.append("?");
        url.append(query);
        return url.toString();
    }

    /**
     * 获取当前请求路径根
     * */
    public static String getContextPath(){
        if (getHttpRequest() != null) {
            String scheme = getHttpRequest().getHeader("x-forwarded-proto");
                if (TxtUtil.isEmpty(scheme)){
                    scheme = getHttpRequest().getScheme();
                }
               String p = scheme + "://" + getHttpRequest().getServerName();
               if ((getHttpRequest().getServerPort() != 80) && (getHttpRequest().getServerPort() != 443)){
                   p = p + ":" + getHttpRequest().getServerPort();
               }
              p = p + getHttpRequest().getContextPath();
              return p;
        }
        return "";
    }

    public static void setCookie(String cookieKey, String cookieValue){
        Cookie cookie = new Cookie(cookieKey,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(10*60);

    }

    public static String getCookie(String cookieKey){
        Cookie[]  cookies = getHttpRequest().getCookies();
        for (Cookie cookie : cookies) {
            if(cookie != null && TxtUtil.isEmpty(cookieKey)){
                if(cookieKey.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public static void write(Object target) {
        try {
            getHttpResponse().setContentType("text/javascript;charset=UTF-8");
            PrintWriter out = getHttpResponse().getWriter();
            out.write(JSON.toJSONString(target));
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("Response writing failure.", e);
        }
    }
    public static void writeXml (String xml) {
        try{
            getHttpResponse().setContentType("text/xml;charset=UTF-8");
            PrintWriter out = getHttpResponse().getWriter();
            out.write(xml);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("Response writing failure.", e);
        }
    }

    public static String getClientIp(){
        HttpServletRequest request = getHttpRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotBlank(ip) && "unKnown".equalsIgnoreCase(ip)){
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static String getAdd() {
        //淘宝IP地址库：http://ip.taobao.com/instructions.php
        String add = null;
        String ip = getClientIp();
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = new URL(TAO_BAO_API + ip);
            URLConnection connection = U.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
            JSONObject jsonObject = JSONObject.parseObject(result);
            Map<String, Object> map = (Map) jsonObject;
            //0：成功，1：失败。
            String code = String.valueOf(map.get("code"));
            //失败
            if ("1".equals(code)) {
                //错误信息
                String data = String.valueOf(map.get("data"));
                return data;
                //成功
            } else if ("0".equals(code)) {
                Map<String, Object> data = (Map<String, Object>) map.get("data");
                //国家
                String country = String.valueOf(data.get("country"));
                String area = String.valueOf(data.get("area"));
                //省（自治区或直辖市）
                String city = String.valueOf(data.get("city"));
                //市（县）
                String region = String.valueOf(data.get("region"));
                add = country + "-" + city + "-" + region;
                return add;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
