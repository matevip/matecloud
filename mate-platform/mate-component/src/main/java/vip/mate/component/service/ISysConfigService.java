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

    OssProperties getOssProperties();

    OssProperties getConfigByCode(String code);

    boolean saveConfigOss(OssProperties ossProperties, String code);

    boolean saveDefaultOss(String code);

    String defaultOss();

}
