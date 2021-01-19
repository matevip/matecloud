package vip.mate.component.service;

import vip.mate.component.entity.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.oss.props.OssProperties;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-08-05
 */
public interface ISysConfigService extends IService<SysConfig> {

	/**
	 * 获取默认主题
	 *
	 * @return OssProperties
	 */
	OssProperties getOssProperties();

	/**
	 * 根据code获取主题信息
	 *
	 * @param code code编码
	 * @return OssProperties
	 */
	OssProperties getConfigByCode(String code);

	/**
	 * 保存配置信息
	 *
	 * @param ossProperties OssProperties
	 * @param code          关键词
	 * @return boolean
	 */
	boolean saveConfigOss(OssProperties ossProperties, String code);

	/**
	 * 修改默认oss
	 *
	 * @param code 关键词
	 * @return boolean
	 */
	boolean saveDefaultOss(String code);

	/**
	 * 获取默认oss的code
	 *
	 * @return code
	 */
	String defaultOss();

	/**
	 * 清理Oss缓存
	 */
	void clearOss();

}
