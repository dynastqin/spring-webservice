package com.dynastqin.cxf.server.impl;

import com.dynastqin.cxf.server.SoapWs.IHelloService;

import javax.jws.WebService;

/**
 * Created by tantao on 14-9-4.
 */
@WebService
public class HelloService implements IHelloService {

    @Override
    public String say(String name) {
        return "hello "+name;
    }
}
