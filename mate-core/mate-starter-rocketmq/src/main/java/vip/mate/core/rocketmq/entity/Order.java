package vip.mate.core.rocketmq.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 演示订单表
 *
 * @author pangu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Order implements Serializable {

	private static final long serialVersionUID = 2011242218927120008L;

	private Long id;

	private Long tradeId;

	private Long goodsId;

	private BigDecimal goodsPrice;

	private Integer number;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

}
