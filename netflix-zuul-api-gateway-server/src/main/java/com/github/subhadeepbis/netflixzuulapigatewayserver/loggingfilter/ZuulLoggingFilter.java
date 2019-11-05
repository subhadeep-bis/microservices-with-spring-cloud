package com.github.subhadeepbis.netflixzuulapigatewayserver.loggingfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is made to handle Zuul Logging i.e. any request that comes through the gateway we will log it
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    /**
     * This is Logger object that will help in logging the details of the request.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * filterType() is to indicate as to when should the filtering be happening i.e
     * before any request is executed i.e. you'll have to return "pre" for that matter
     * or,
     * after the request has executed i.e. you'll have to return "post" for that matter
     * or,
     * filter only error requests i.e. you'll have to return "error" for that matter.
     * In our case we would want to filter all the requests before execution, do we will return "pre".
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * If you have multiple filters say, zuul logging filter, zuul security filter and a lot of other filters
     * you can set a priority order between them
     * for this case we are returning 1, which means it has the highest priority.
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * This defines whether the filter would be executed or not.
     * You can actually implement business logic to check the incoming and request and decide by observing
     * on certain parameters whether to execute the filter or not.
     * for now we want to execute this filter for every request
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *  Here goes the real logic of interception.
     *  motive: I want to log all the details of the request.
     *  The motive is generally not only logging the request, you can implement other things over here also.
     *  e.g. Implement Security, implement rate limiting etc.
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        /**
         * for the above motive to get fulfilled we will have to first get the request and then log it.
         */
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
        return null;
    }
}
