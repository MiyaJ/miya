package com.miya.order.feignclient.fallback;

import com.miya.entity.MiyaResponse;
import com.miya.order.feignclient.WarehouseServiceFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Caixiaowei
 * @ClassName WarehouseServiceFallback
 * @Description
 * @createTime 2021/4/29 11:31
 */
@Slf4j
@Component
public class MemberServiceFallback implements FallbackFactory<MemberServiceFallback> {

    @Override
    public MemberServiceFallback create(Throwable throwable) {
        return null;
    }
}
