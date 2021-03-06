package com.nepxion.discovery.plugin.example.zuul.impl;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MyZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        // 来自于外界的Header，例如，从Postman传递过来的Header
        String version = context.getRequest().getHeader(DiscoveryConstant.N_D_VERSION);
        if (StringUtils.isEmpty(version)) {
            context.addZuulRequestHeader(DiscoveryConstant.N_D_VERSION, "{\"discovery-springcloud-example-a\":\"1.0\", \"discovery-springcloud-example-b\":\"1.0\", \"discovery-springcloud-example-c\":\"1.0;1.2\"}");
        }

        return null;
    }
}