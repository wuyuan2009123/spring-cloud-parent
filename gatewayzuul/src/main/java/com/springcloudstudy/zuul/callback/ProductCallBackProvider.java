package com.springcloudstudy.zuul.callback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by wuy on 2017/7/27.
 */
@Component
public class ProductCallBackProvider implements ZuulFallbackProvider {
    @Override
    public String getRoute() {
        return "product";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value();
            }
            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase();
            }
            @Override
            public void close() {
            }
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("用户微服务不可用,请稍后重试".getBytes("UTF-8"));
            }
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers=new HttpHeaders();
                MediaType type=new MediaType("application","json", Charset.forName("UTF-8"));
                headers.setContentType(type);
                return headers;
            }
        };
    }
}
