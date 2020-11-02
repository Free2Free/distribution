package xin.altitude.lock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xin.altitude.lock.entity.Goods;
import xin.altitude.lock.mappeer.GoodsMapper;

import javax.annotation.Resource;

/**
 * @author explore
 * @date 2020/11/02 15:42
 */
@Service
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 通过商品ID查询商品信息
     * @param id
     * @return
     */
    public Goods getGoodsById(Long id){
        return goodsMapper.selectById(id);
    }
}
