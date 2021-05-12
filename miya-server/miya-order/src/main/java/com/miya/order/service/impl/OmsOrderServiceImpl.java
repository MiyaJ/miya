package com.miya.order.service.impl;

import com.miya.entity.MiyaResponse;
import com.miya.entity.order.OmsOrder;
import com.miya.order.mapper.OmsOrderMapper;
import com.miya.order.model.OrderCreateParam;
import com.miya.order.service.IOmsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2021-05-08
 */
@Service
@Slf4j
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    /**
     * 创建订单
     *
     * @param orderCreateParam 创建订单参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/5/8 11:41
     */
    @Override
    public MiyaResponse create(OrderCreateParam orderCreateParam) {

        // TODO: 2021/5/8 开启 seata 分布式事务

        // TODO: 2021/5/8 生成订单

        // TODO: 2021/5/8 增加会员积分

        // TODO: 2021/5/8 减少sku商品库存

        // TODO: 2021/5/8 根据分支事务结果来提交或者回滚

        return null;
    }
}
