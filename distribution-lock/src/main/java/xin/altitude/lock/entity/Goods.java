package xin.altitude.lock.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="tb_goods")
public class Goods {

	// 主键ID（全局唯一）
	@TableId
	private Long id;

	// 商品名称
	private String name;

	// 价格
	private Integer price;

	// 库存
	private Integer stock;

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


}
