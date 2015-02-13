package com.dynastqin.cxf.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by tantao on 14-9-5.
 */
public class HttpClientUtils {

    /**用户账号mock*/
    public enum Account{
        USER("tant","123456"),
        ADMIN("admin","123456")
        ;

        private String userName;
        private String userPwd;

        Account(String userName, String userPwd) {
            this.userName = userName;
            this.userPwd = userPwd;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserPwd() {
            return userPwd;
        }
    }

    public static class Result {
        private int code;
        private String data;


        public Result(int code, String data) {
            this.code = code;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public String getData() {
            return data;
        }
    }

    /**
     * 获取basic authentication用户身份信息字符串(使用base64加密)
     * @param account 用户账号
     * @return
     */
    public static String getBase64AuthorHeader(Account account){
        String auth = account.getUserName() +":"+ account.getUserPwd();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));//base64加密
        return "Basic " + new String(encodedAuth);
    }

    /**
     * 获取json格式入参的HttpClient
     *
     * @param uri
     * @param methodType (取值：HttpMethod.POST 或 HttpMethod.PUT)
     * @param jsonData
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Result executePostOrPutJsonMethod(String uri, String methodType, String jsonData) {
        EntityEnclosingMethod method = null;
        if (methodType == HttpMethod.POST) {
            method = new PostMethod(uri);
        } else if (methodType == HttpMethod.PUT) {
            method = new PutMethod(uri);
        } else {
            return null;
        }
        int code = -1;
        try {
            byte[] b = jsonData.getBytes(HttpConstants.UTF_8);
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length, HttpConstants.APPLICATION_JSON + HttpConstants.CHARSET + HttpConstants.UTF_8);
            method.setRequestEntity(re);
            HttpClient client = new HttpClient();

            //传入用户身份信息
            method.setRequestHeader(HttpHeaders.AUTHORIZATION, HttpClientUtils.getBase64AuthorHeader(Account.ADMIN));

            client.getParams().setContentCharset(HttpConstants.UTF_8);//保证返回值不为中文乱码
            code = client.executeMethod(method);
            return new Result(code, method.getResponseBodyAsString());
        } catch (Exception ex) {
            return new Result(code, ex.getMessage());
        } finally {
            method.releaseConnection();
        }

    }
}
