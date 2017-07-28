package com.springcloudstudy.zuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuy on 2017/7/27.
 */
public class PreRequestPostFilter  extends ZuulFilter {
    private static final Logger LOGGER= LoggerFactory.getLogger(PreRequestPostFilter.class);

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest request= ctx.getRequest();
        String traceId=(String) request.getAttribute("traceId");
        PreRequestPostFilter.LOGGER.info(request.getRequestURI().toString()+" , method: "+request.getMethod()+"  , traceId :"+traceId );
        return null;
    }
}
