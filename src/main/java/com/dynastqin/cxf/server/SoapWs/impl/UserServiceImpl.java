package com.dynastqin.cxf.server.SoapWs.impl;

import javax.jws.WebService;

import com.dynastqin.cxf.server.SoapWs.IUserService;
import com.dynastqin.cxf.server.SoapWs.WsConstants;
import com.dynastqin.cxf.server.SoapWs.dto.UserDto;

/**
 * WebService服务端实现类.
 */
//serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称,
//endpointInterface属性指向Interface类全称.
@WebService(serviceName = "userService",
        endpointInterface = "com.dynastqin.cxf.server.SoapWs.IUserService",
        targetNamespace = WsConstants.NS)
public class UserServiceImpl implements IUserService {
    @Override
    public UserDto getUser(String userId) {
        UserDto dto = new UserDto();
        dto.setId(Long.parseLong("1001"));
        dto.setLoginName("dongwq");
        dto.setName("张三");
        dto.setEmail("dongwq@qq.com");
        return dto;
    }

    @Override
    public String getUserName(String userId) {
        return "hello tom! your userId is:"+userId;
    }
}