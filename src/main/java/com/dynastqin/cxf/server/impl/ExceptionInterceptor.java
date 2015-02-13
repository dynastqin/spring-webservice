
package com.dynastqin.cxf.server.impl;

import com.dynastqin.cxf.util.HttpClientUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            HttpClientUtils.Result result = new HttpClientUtils.Result(-1, ex.getMessage());
            logger.error("webservice调用异常：", ex);
            return result;
        }
    }
}
