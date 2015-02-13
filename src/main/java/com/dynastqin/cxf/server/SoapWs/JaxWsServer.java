package com.dynastqin.cxf.server.SoapWs;

import com.dynastqin.cxf.server.SoapWs.IHelloService;
import com.dynastqin.cxf.server.impl.HelloService;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 *
 * Created by tantao on 14-9-4.
 */
public class JaxWsServer {

    public static void main(String[] args) {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8081/ws/soap/hello");
        factory.setServiceClass(IHelloService.class);
        factory.setServiceBean(new HelloService());
        factory.create();
        System.out.println("soap ws is published");
    }
}
