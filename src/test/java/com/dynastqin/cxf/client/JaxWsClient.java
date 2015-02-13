package com.dynastqin.cxf.client;

import com.dynastqin.cxf.server.SoapWs.IHelloService;
import com.dynastqin.cxf.server.SoapWs.IUserService;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * Created by tantao on 14-9-4.
 */
public class JaxWsClient {

    public static void main(String[] args) {
        dynamicExecute();

    }

    /**
     * 通过代理API调用，依赖于服务端的接口
     */
    public static void proxyExecute(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress("http://localhost:8089/springws/ws/soap/userService");
        factory.setServiceClass(IUserService.class);

        IUserService service = factory.create(IUserService.class);
        String result = service.getUserName("1");
        System.out.println(result);
    }

    /**
     * 不依赖服务器端接口来完成调用的，也就是不仅仅能调用Java的接口
     */
    public static void dynamicExecute() {
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://localhost:8089/springws/ws/soap/userService?wsdl");
        try {
            Object[] result = new Object[0];
            result = client.invoke("getUser", "1001");
            System.out.println(result[0]);
        } catch (Exception e) {
        }

    }
}