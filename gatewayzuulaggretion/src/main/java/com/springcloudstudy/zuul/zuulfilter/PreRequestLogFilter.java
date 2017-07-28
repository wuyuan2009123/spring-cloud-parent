package com.springcloudstudy.zuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by wuy on 2017/7/27.
 */


public class PreRequestLogFilter extends ZuulFilter {

    private static final Logger LOGGER= LoggerFactory.getLogger(PreRequestLogFilter.class);

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest request= ctx.getRequest();
        String requestId= UUID.randomUUID().toString();
        request.setAttribute("traceId",""+requestId);
        PreRequestLogFilter.LOGGER.info(request.getRequestURI().toString()+" , method: "+request.getMethod()+" ,requestId: "+requestId);
        return null;
    }
}
