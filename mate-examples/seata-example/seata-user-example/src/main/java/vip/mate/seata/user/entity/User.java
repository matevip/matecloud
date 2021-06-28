package vip.mate.seata.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户表
 *
 * @author pangu
 */
@Data
@TableName("mate_demo_user")
public class User {
	@TableId
	Integer id;
	String name;
	int age;
}
