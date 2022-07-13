package org.jeecg.modules.vote.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WeChatUtil
 * @Auther: Yuu
 * @Date: 2020/8/21 11:17
 * @Description: 微信工具类
 */
@Component
public class WeChatUtil {


    /**
     * appid
     */
    @Value(value = "${wxapplets.appid}")
    private String appid;

    /**
     * secret
     */
    @Value(value = "${wxapplets.secret}")
    private String secret;

    /**
     * customerAppid
     */
    @Value(value = "${wxapplets.customerappid}")
    private String customerAppid;

    /**
     * customerSecret
     */
    @Value(value = "${wxapplets.customersercret}")
    private String customerSecret;


    /**
     * publicAppid
     */
    @Value(value = "${wxapplets.publicappid}")
    private String publicAppid;

    /**
     * publicSecret
     */
    @Value(value = "${wxapplets.publicsercret}")
    private String publicSecret;


    @Autowired
    private RedisUtil redisUtil;


    public JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", appid);
        //小程序secret
        requestUrlParam.put("secret", secret);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public String getWxMiniPhone(String sessionkey, String iv, String encryptedData)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        byte[] encrypData = Base64Utils.decodeFromString(encryptedData);
        byte[] ivData = Base64Utils.decodeFromString(iv);
        byte[] sessionKey = Base64Utils.decodeFromString(sessionkey);
        String resultString = null;
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        SecretKeySpec keySpec = new SecretKeySpec(sessionKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        resultString = new String(cipher.doFinal(encrypData), "UTF-8");
        JSONObject object = JSONObject.parseObject(resultString);
        // 拿到手机号码
        String phone = object.getString("phoneNumber");
        return phone;
    }

    public JSONObject getCustomSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", customerAppid);
        //小程序secret
        requestUrlParam.put("secret", customerSecret);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }


    /**
     * 公众号获取 AccessToken
     *
     * @return
     */
    public String getParentAccessToken() {
        // 获取 token Url
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>();
        // 公众号 appId
        requestUrlParam.put("appid", customerAppid);
        //公众号 secret
        requestUrlParam.put("secret", customerSecret);
        //默认参数
        requestUrlParam.put("grant_type", "client_credential");
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doGet(requestUrl, requestUrlParam));

        // 存入 Redis
        String accessToken = (String) jsonObject.get("access_token");
        redisUtil.set("WECHAT_ACCESS_TOKEN", accessToken, 7000);
        return accessToken;
    }





}
