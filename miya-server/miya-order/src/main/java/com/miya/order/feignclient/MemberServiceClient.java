package com.miya.order.feignclient;

import com.miya.order.feignclient.fallback.MemberServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author Caixiaowei
 * @ClassName MemberServiceClient
 * @Description 会员feign 客户端
 * @createTime 2021/5/8 14:28
 */
@Component
@FeignClient(value = "miya-member", fallbackFactory = MemberServiceFallback.class)
public interface MemberServiceClient {


}
