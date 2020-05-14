package com.miya.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.miya.constant.MiyaConstant;
import com.miya.entity.MiyaResponse;
import com.miya.gateway.properties.MiyaGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * @author Caixiaowei
 * @ClassName MiyaGatewayRequestFilter.java
 * @Description TODO
 * @createTime 2020年05月12日 14:53:00
 */
@Slf4j
@Component
public class MiyaGatewayRequestFilter implements GlobalFilter {

    @Autowired
    private MiyaGatewayProperties properties;
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 禁止客户端的访问资源逻辑
        Mono<Void> checkForbidUriResult = checkForbidUri(request, response);
        if (checkForbidUriResult != null) {
            return checkForbidUriResult;
        }

        // 打印转发日志
        printLog(exchange);

        byte[] token = Base64Utils.encode((MiyaConstant.GATEWAY_TOKEN_VALUE).getBytes());
        ServerHttpRequest build = request.mutate().header(MiyaConstant.GATEWAY_TOKEN_HEADER, new String(token)).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }

    /**
     * @title 校验禁止访问的请求
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 15:07
     * @throws
     */
    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            return makeResponse(response, MiyaResponse.error("该URI不允许外部访问"));
        }
        return null;
    }

    /**
     * @title 生成返回响应
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 15:08
     * @throws
     */
    private Mono<Void> makeResponse(ServerHttpResponse response, MiyaResponse miyaResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(miyaResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * @title 打印转发日志
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 15:10
     * @throws
     */
    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }
    }
}
