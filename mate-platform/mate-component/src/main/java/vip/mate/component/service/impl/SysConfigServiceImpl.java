package vip.mate.component.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import vip.mate.component.entity.SysConfig;
import vip.mate.component.mapper.SysConfigMapper;
import vip.mate.component.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.core.common.constant.ComponentConstant;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.oss.props.OssProperties;

import java.util.List;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-08-05
 */
@Service
@AllArgsConstructor
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public OssProperties getOssProperties() {
        OssProperties oss = new OssProperties();
        //获取默认的code值
        String code = getDefaultSysConfig().getValue();
        //读取默认code的配置参数
        LambdaQueryWrapper<SysConfig> sysConfigLambdaQueryWrapper = Wrappers.<SysConfig>query().lambda().eq(SysConfig::getCode, code);
        List<SysConfig> sysConfigList = this.baseMapper.selectList(sysConfigLambdaQueryWrapper);
        oss = listToProps(sysConfigList, oss);
        redisTemplate.opsForValue().set(ComponentConstant.OSS_DEFAULT, oss);
        return oss;
    }

    @Override
    public OssProperties getConfigByCode(String code) {
        OssProperties oss = new OssProperties();
        LambdaQueryWrapper<SysConfig> sysConfigLambdaQueryWrapper = Wrappers.<SysConfig>query().lambda().eq(SysConfig::getCode, code);
        List<SysConfig> sysConfigList = this.baseMapper.selectList(sysConfigLambdaQueryWrapper);
        oss = listToProps(sysConfigList, oss);
        //对oss部分字段进行隐藏显示，保护隐私
        oss.setSecretKey(StringUtil.hide(oss.getSecretKey(), 3, 23));
        return oss;
    }

    @Override
    public boolean saveConfigOss(OssProperties ossProperties, String code) {

        LambdaUpdateWrapper<SysConfig> lsc = Wrappers.<SysConfig>update().lambda()
                .set(SysConfig::getValue, ossProperties.getEndpoint())
                .eq(SysConfig::getCode, code)
                .eq(SysConfig::getCKey, ComponentConstant.OSS_ENDPOINT);
        this.update(lsc);

        lsc = Wrappers.<SysConfig>update().lambda()
                .set(SysConfig::getValue, ossProperties.getCustomDomain())
                .eq(SysConfig::getCode, code)
                .eq(SysConfig::getCKey, ComponentConstant.OSS_CUSTOM_DOMAIN);
        this.update(lsc);

        lsc = Wrappers.<SysConfig>update().lambda()
                .set(SysConfig::getValue, ossProperties.getAccessKey())
                .eq(SysConfig::getCode, code)
                .eq(SysConfig::getCKey, ComponentConstant.OSS_ACCESS_KEY);
        this.update(lsc);

        lsc = Wrappers.<SysConfig>update().lambda()
                .set(SysConfig::getValue, ossProperties.getSecretKey())
                .eq(SysConfig::getCode, code)
                .eq(SysConfig::getCKey, ComponentConstant.OSS_SECRET_KEY);
        this.update(lsc);

        lsc = Wrappers.<SysConfig>update().lambda()
                .set(SysConfig::getValue, ossProperties.getBucketName())
                .eq(SysConfig::getCode, code)
                .eq(SysConfig::getCKey, ComponentConstant.OSS_BUCKET_NAME);
        //更新配置文件至redis
        this.getOssProperties();
        return this.update(lsc);
    }

    @Override
    public boolean saveDefaultOss(String code) {
        LambdaUpdateWrapper<SysConfig> lsc = Wrappers.<SysConfig>update().lambda()
                .set(SysConfig::getValue, code)
                .eq(SysConfig::getCKey, ComponentConstant.CODE_DEFAULT)
                .eq(SysConfig::getCode, ComponentConstant.OSS_DEFAULT);
        boolean flag = this.update(lsc);
        if (flag) {
            //更新配置文件至redis
            this.getOssProperties();
        }
        return flag;
    }

    @Override
    public String defaultOss() {
        return getDefaultSysConfig().getValue();
    }

    public SysConfig getDefaultSysConfig() {
        //获取默认的oss配置
        LambdaQueryWrapper<SysConfig> lsc = Wrappers.<SysConfig>query().lambda().eq(SysConfig::getCode, ComponentConstant.OSS_DEFAULT);
        return this.baseMapper.selectOne(lsc);
    }

    public OssProperties listToProps(List<SysConfig> sysConfigList, OssProperties oss) {
        //给OssProperties赋值
        for (SysConfig s : sysConfigList) {
            if (s.getCKey().equals(ComponentConstant.OSS_ENDPOINT)) {
                oss.setEndpoint(s.getValue());
            } else if (s.getCKey().equals(ComponentConstant.OSS_CUSTOM_DOMAIN)) {
                oss.setCustomDomain(s.getValue());
            } else if (s.getCKey().equals(ComponentConstant.OSS_ACCESS_KEY)) {
                oss.setAccessKey(s.getValue());
            } else if (s.getCKey().equals(ComponentConstant.OSS_SECRET_KEY)) {
                oss.setSecretKey(s.getValue());
            } else if (s.getCKey().equals(ComponentConstant.OSS_PATH_STYLE_ACCESS)) {
                oss.setPathStyleAccess(Boolean.valueOf(s.getValue()));
            } else if (s.getCKey().equals(ComponentConstant.OSS_BUCKET_NAME)) {
                oss.setBucketName(s.getValue());
            }

        }
        return oss;
    }
}
