package com.dynastqin.cxf.client;

import com.dynastqin.cxf.util.HttpClientUtils;
import com.dynastqin.cxf.util.HttpConstants;
import com.dynastqin.cxf.util.JacksonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.After;
import org.junit.Test;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * 测试JAX-RS(RESTful)的webService
 * Created by tantao on 14-9-4.
 */
public class JaxrWsClient {

    private final static String baseAddress = "http://10.1.18.2:8089/ws/rest";
    private GetMethod getMethod = null;
    private DeleteMethod deleteMethod = null;

    @After
    public void destroy() {
        if (null != getMethod)
            getMethod.releaseConnection();
        if (null != deleteMethod)
            deleteMethod.releaseConnection();

    }

    @Test
    public void testGetAll() {
        getMethod = new GetMethod(baseAddress + "/products");
        getMethod.setRequestHeader(HttpHeaders.ACCEPT, HttpConstants.APPLICATION_JSON);
        //传入用户身份信息
        getMethod.setRequestHeader(HttpHeaders.AUTHORIZATION, HttpClientUtils.getBase64AuthorHeader(HttpClientUtils.Account.USER));

        HttpClient client = new HttpClient();
        client.getParams().setContentCharset(HttpConstants.UTF_8);//保证反正数据无中文乱码
        try {
            int code = client.executeMethod(getMethod);
            if (code == HttpStatus.SC_OK) {
                String responseJson=getMethod.getResponseBodyAsString();
                System.out.println(responseJson);
               List<ClientProduct> products=JacksonUtil.json2List(responseJson,ClientProduct[].class);
                System.out.println(products);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("测试失败！");

        }
    }

    @Test
    public void testGetByName() {
        getMethod = new GetMethod(baseAddress + "/products/iphone");
        getMethod.setRequestHeader(HttpHeaders.ACCEPT, HttpConstants.APPLICATION_JSON);
        //传入用户身份信息
        getMethod.setRequestHeader(HttpHeaders.AUTHORIZATION, HttpClientUtils.getBase64AuthorHeader(HttpClientUtils.Account.USER));

        HttpClient client = new HttpClient();
        client.getParams().setContentCharset(HttpConstants.UTF_8);
        try {
            int code = client.executeMethod(getMethod);
            if (code == HttpStatus.SC_OK) {
                System.out.println(getMethod.getResponseBodyAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        String uri = baseAddress + "/product";
        ClientProduct toAdd = new ClientProduct(3, "MX4", "魅族");
        String jsonData = JacksonUtil.entity2Json(toAdd);
        HttpClientUtils.Result result = HttpClientUtils.executePostOrPutJsonMethod(uri, HttpMethod.POST, jsonData);
        if (result.getCode() == HttpStatus.SC_OK) {
            System.out.println(result.getData());
        }
    }

    @Test
    public void testUpdate() {
        String uri = baseAddress + "/product/1";
        ClientProduct toUpdate = new ClientProduct(1, "chrome", "google");
        String jsonData = JacksonUtil.entity2Json(toUpdate);
        HttpClientUtils.Result result = HttpClientUtils.executePostOrPutJsonMethod(uri, HttpMethod.PUT, jsonData);
        if (result.getCode() == HttpStatus.SC_OK) {
            System.out.println(result.getData());
        }
    }

    @Test
    public void testDeleteById() {
        deleteMethod = new DeleteMethod(baseAddress + "/product/1");
        deleteMethod.setRequestHeader(HttpHeaders.ACCEPT, HttpConstants.APPLICATION_JSON);
        //传入用户身份信息
        deleteMethod.setRequestHeader(HttpHeaders.AUTHORIZATION, HttpClientUtils.getBase64AuthorHeader(HttpClientUtils.Account.ADMIN));

        HttpClient client = new HttpClient();
        client.getParams().setContentCharset(HttpConstants.UTF_8);
        try {
            int code = client.executeMethod(deleteMethod);
            if (code == HttpStatus.SC_OK) {
                System.out.println(deleteMethod.getResponseBodyAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
