package xin.altitude.lock.mappeer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xin.altitude.lock.entity.Goods;
import xin.altitude.lock.entity.Order;

/**
 * @author explore
 * @date 2020/11/02 13:46
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
