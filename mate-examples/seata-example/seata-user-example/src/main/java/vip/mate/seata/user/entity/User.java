package vip.mate.seata.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户表
 *
 * @author pangu
 */
@Data
public class User {
	@TableId
	Integer id;
	String name;
	int age;
}
