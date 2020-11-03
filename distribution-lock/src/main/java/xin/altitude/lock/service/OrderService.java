package xin.altitude.lock.service;

import lombok.SneakyThrows;
import lombok.Synchronized;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private RedissonClient redissonClient;


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


    /**
     * 使用zk分布式锁，解决并发问题，将压力挡在数据库访问之前
     * @param goodsId
     * @param num
     * @return
     */
    @SneakyThrows
    @Transactional
    public Boolean genOrderWithZk(Long goodsId,int num){
        InterProcessMutex lock = new InterProcessMutex(curatorFramework,"/lock");
        //加锁（分布式）
        lock.acquire();
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods.getStock()>=num) {
            goods.setStock(goods.getStock()-num);
            //减库存(基于乐观锁实现减库存)
            boolean bl = goodsMapper.updateById(goods) > 0;
            if (bl) {
                Order order = new Order(goodsId, num);
                orderMapper.insert(order);
                lock.release();
                return true;
            }
        }
        //释放锁
        lock.release();
        return false;
    }

    @Transactional
    public Boolean genOrderWithRedis(Long goodsId,int num){
        String key = "dec_store_lock_" + goodsId;
        RLock lock = redissonClient.getLock(key);
        //加锁（分布式）
        lock.lock();
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods.getStock()>=num) {
            goods.setStock(goods.getStock()-num);
            //减库存(基于乐观锁实现减库存)
            boolean bl = goodsMapper.updateById(goods) > 0;
            if (bl) {
                Order order = new Order(goodsId, num);
                orderMapper.insert(order);
                //释放锁
                lock.unlock();
                return true;
            }
        }
        //释放锁
        lock.unlock();
        return false;
    }
}
