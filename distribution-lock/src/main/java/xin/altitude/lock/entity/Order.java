package xin.altitude.lock.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="tb_order")
public class Order {

	// 主键ID（全局唯一）
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	// 商品ID
	private Long goodsId;

	// 商品数量
	private Integer num;

	// 逻辑删除（0:未删除；1:已删除）
	@TableField(select = false)
	private String deleted;

	// 乐观锁
	@Version
	private Integer version;

	// 创建时间
	private java.util.Date gmtCreate;

	// 修改时间
	private java.util.Date gmtModified;

	public Order(Long goodsId, Integer num) {
		this.goodsId = goodsId;
		this.num = num;
	}
}
