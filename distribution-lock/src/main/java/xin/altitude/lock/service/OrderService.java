package xin.altitude.lock.service;

import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.altitude.lock.entity.Goods;
import xin.altitude.lock.entity.Order;
import xin.altitude.lock.mappeer.GoodsMapper;
import xin.altitude.lock.mappeer.OrderMapper;

/**
 * @author explore
 * @date 2020/11/02 13:52
 */
@Service
public class OrderService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 生成订单
     * @param goodsId 商品ID
     * @param num 下单数量
     * @return
     */
    @Transactional
    public Boolean genOrder(Long goodsId,int num){
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods.getStock()>=num) {
            goods.setStock(goods.getStock()-num);
            //减库存(基于乐观锁实现减库存)
            boolean bl = goodsMapper.updateById(goods) > 0;
            if (bl) {
                Order order = new Order(goodsId, num);
                orderMapper.insert(order);
                return true;
            }
            return false;
        }
        return false;
    }
}
