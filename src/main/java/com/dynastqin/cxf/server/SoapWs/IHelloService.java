package com.dynastqin.cxf.server.SoapWs;

import javax.jws.WebService;

/**
 * Created by tantao on 14-9-4.
 */
@WebService
public interface IHelloService {

    String say(String name);
}
