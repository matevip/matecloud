package vip.mate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.database.entity.Search;
import vip.mate.system.entity.SysClient;
import vip.mate.system.poi.SysClientPOI;

import java.util.List;

/**
 * <p>
 * 客户端表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-07-14
 */
public interface ISysClientService extends IService<SysClient> {

	/**
	 * 查询分页列表
	 *
	 * @param search 查询参数
	 * @return IPage
	 */
	IPage<SysClient> listPage(Search search);

	/**
	 * 设置状态
	 *
	 * @param ids    ID串，以逗号分隔
	 * @param status 状态
	 * @return boolean
	 */
	boolean status(String ids, String status);

	/**
	 * 导出
	 *
	 * @return List
	 */
	List<SysClientPOI> export();
}
