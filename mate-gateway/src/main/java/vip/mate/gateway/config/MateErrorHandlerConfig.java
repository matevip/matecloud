/*
 * Copyright 2019-2028 Beijing Daotiandi Technology Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Author: xuzhanfu (7333791@qq.com)
 */
package vip.mate.gateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;
import vip.mate.gateway.handler.MateExceptionHandler;

import java.util.Collections;
import java.util.List;

/**
 * @author xuzhanfu
 * @date 2019-10-07 20:15
 * @description: 错误处理配置类 备用
 **/
//@Configuration
//@AutoConfigureBefore(ErrorWebFluxAutoConfiguration.class)
//@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class})
public class MateErrorHandlerConfig {

    private final ServerProperties serverProperties;

    private final ResourceProperties resourceProperties;

    private final ApplicationContext applicationContext;

    private final List<ViewResolver> viewResolverList;

    private final ServerCodecConfigurer serverCodecConfigurer;

    public MateErrorHandlerConfig(ServerProperties serverProperties,
                                  ResourceProperties resourceProperties,
                                  ApplicationContext applicationContext,
                                  ObjectProvider<List<ViewResolver>> objectProvider,
                                  ServerCodecConfigurer serverCodecConfigurer){
        this.serverProperties = serverProperties;
        this.resourceProperties = resourceProperties;
        this.applicationContext = applicationContext;
        this.viewResolverList = objectProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;

    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler (ErrorAttributes errorAttributes){
        MateExceptionHandler mateExceptionHandler = new MateExceptionHandler(
                errorAttributes,
                this.resourceProperties,
                this.serverProperties.getError(),
                this.applicationContext);
        mateExceptionHandler.setViewResolvers(this.viewResolverList);
        mateExceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
        mateExceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
        return mateExceptionHandler;
    }
}
