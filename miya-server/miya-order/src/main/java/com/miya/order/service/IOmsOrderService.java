package com.miya.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.MiyaResponse;
import com.miya.entity.order.OmsOrder;
import com.miya.order.model.OrderCreateParam;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2021-05-08
 */
public interface IOmsOrderService extends IService<OmsOrder> {

    /**
     * 创建订单
     *
     * @param orderCreateParam 创建订单参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/5/8 11:41
     */
    MiyaResponse create(OrderCreateParam orderCreateParam);
}
