/*
 *
 *  Copyright 2020-2030, MateCloud-Plus,Beijing DaoTianDi Technology Inc. All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *   Neither the name of the mate.vip developer nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *   Author: pangu(7333791@qq.com)
 *
 *   此软件为商业软件，未经购买不得使用
 *   禁止将本产品的部分或全部代码和资源进行任何形式的开源（尤其上传GitHub、Gitee等开源平台）
 *   由此衍生的源代码的知识产权由购买方拥有，但是未经DaoTianDi官方许可，不得将修改后的源代码提供给任何第三方
 *
 */

package vip.mate.core.swagger.props;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * swagger 配置增强
 *
 * @author matevip
 * @since 2022-11-6
 */
@Data
@ConfigurationProperties(prefix = "springdoc.plus")
public class SwaggerProperties {

    private Boolean enabled = true;

    /**
     * 为什么不用 swagger v3 自带的呢？因为idea不提示 防止有人不会写
     */
    private PlusInfo info;
    private ExternalDocumentation external;
    /**
     * 主要适配 oauth2（http basic配置起来更容易,可以参考oauth2 配置）
     */
    private Map<String, SecurityScheme> securitySchemes;
    /**
     * serverUrl 默认 本地地址
     **/
    private List<Server> servers;

    @Data
    public static class PlusInfo {
        private String title = null;
        private String description = null;
        private String termsOfService = null;
        private PlusContact contact = null;
        private PlusLicense license = null;
        private String version = null;
    }

    @Data
    public static class PlusContact {
        private String name = null;
        private String url = null;
        private String email = null;
    }

    @Data
    public static class PlusLicense {
        private String name = null;
        private String url = null;
    }
}
