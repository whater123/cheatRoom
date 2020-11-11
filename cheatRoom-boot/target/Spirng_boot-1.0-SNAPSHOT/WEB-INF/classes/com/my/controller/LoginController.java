package com.my.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.constant.LoginQQConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录逻辑处理
 *
 * @author kai_qian
 * @version v1.0
 * @since 2020/02/18 13:55
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 处理登录事件,点击前端的图片会访问到这个方法
     */
    @RequestMapping("/loginByQQ")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String responseType = "code";
        String clientId = LoginQQConstant.APP_ID;
        // 官方文档强调此处要对url进行URLEncode，我试了一下，直接使用原始回调地址也能正常使用
//        String redirectUri = URLEncoder.encode(LoginQQConstant.CALLBACK_URL, StandardCharsets.UTF_8);
        String redirectUri = LoginQQConstant.CALLBACK_URL;
        //client端的状态值。用于第三方应用防止CSRF攻击。
        String state = new Date().toString();
        req.getSession().setAttribute("state", state);

        String url = String.format(LoginQQConstant.GET_AUTHORIZATION_CODE +
                "?response_type=%s&client_id=%s&redirect_uri=%s&state=%s", responseType, clientId, redirectUri, state);

        resp.sendRedirect(url);

        // 如果一切顺利，就会进入callbackHandler方法
    }

    /**
     * 用户授权后的回调方法
     */
    @ResponseBody
    @RequestMapping("/callbackHandler")
    public String callbackHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 1.获取回调的authorization
        String authorizationCode = req.getParameter("code");
        if (authorizationCode == null || authorizationCode.trim().isEmpty()) {
            throw new RuntimeException("未获取到AuthorizationCode");
        }
        // 2.client端的状态值。用于第三方应用防止CSRF攻击。
        String state = req.getParameter("state");
        if (!state.equals(req.getParameter("state"))) {
            throw new RuntimeException("client端的状态值不匹配！");
        }

        // 3.获取accessToken
        String urlForAccessToken = getUrlForAccessToken(authorizationCode);
        String accessToken = getAccessToken(urlForAccessToken);

        // 4.根据accessToken获取openId
        if (accessToken == null || accessToken.trim().isEmpty()) {
            throw new RuntimeException("未获取到accessToken");
        }
        String openid = getOpenId(accessToken);

        // 5.根据openid获取用户信息
        if (openid == null || openid.trim().isEmpty()) {
            throw new RuntimeException("未获取到openId");
        }
        String userInfo = getUserInfo(openid,accessToken);
        return "userInfo为：" + userInfo;

        // ... 获取到用户信息就可以进行自己的业务逻辑处理了
    }

    // 下面是辅助方法

    /**
     * 拼接用于获取accessToken的链接
     */
    private String getUrlForAccessToken(String authorizationCode) throws UnsupportedEncodingException {
        String grantType = "authorization_code";
        String clientId = LoginQQConstant.APP_ID;
        String clientSecret = LoginQQConstant.APP_KEY;
//        String redirect_uri = URLEncoder.encode(CALLBACK_URL, "UTF-8"); 此处进行URLEncode会导致无法获取AccessToken
        String callbackUrl = LoginQQConstant.CALLBACK_URL;

        return String.format(LoginQQConstant.GET_ACCESS_TOKEN +
                        "?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
                grantType, clientId, clientSecret, authorizationCode, callbackUrl);
    }

    /**
     * 获取accessToken
     */
    private String getAccessToken(String urlForAccessToken) {
        //第一次用服务器发起模拟客户端访问,得到的是包含access_token的字符串,其格式如下
        //access_token=0FFD92ABD1DFD4F5&expires_in=7776000&refresh_token=04CE5D1F1E290B0974C5
        String firstCallbackInfo = restTemplate.getForObject(urlForAccessToken, String.class);
        String[] params = firstCallbackInfo.split("&");
        String access_token = null;
        for (String param : params) {
            String[] keyvalue = param.split("=");
            if ("access_token".equals(keyvalue[0])) {
                access_token = keyvalue[1];
                break;
            }
        }
        return access_token;
    }

    /**
     * 根据accessToken获取openid
     */
    private String getOpenId(String access_token) throws IOException {
        String url = String.format(LoginQQConstant.GET_OPEN_ID + "?access_token=%s", access_token);
        //第二次模拟客户端发出请求后得到的是带openid的返回数据,格式如下
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String secondCallbackInfo = restTemplate.getForObject(url, String.class);

        //正则表达式处理
        String regex = "\\{.*\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(secondCallbackInfo);
        if (!matcher.find()) {
            throw new RuntimeException("异常的回调值: " + secondCallbackInfo);
        }

        //调用jackson
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(matcher.group(0), HashMap.class);

        //return "获取到的openid为：" + openid;
        return ((String) hashMap.get("openid"));
    }

    /**
     * 根据openid获取用户信息
     */
    private String getUserInfo(String openid,String access_token) {
        String infoUrl = String.format(LoginQQConstant.GET_USER_INFO + "?access_token=%s&oauth_consumer_key=%s&openid=%ss", access_token, LoginQQConstant.APP_ID, openid);
        return restTemplate.getForObject(infoUrl, String.class);
    }
}
