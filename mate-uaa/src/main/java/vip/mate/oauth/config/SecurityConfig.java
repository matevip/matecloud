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
package vip.mate.oauth.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import vip.mate.core.security.config.IgnoreUrlPropsConfig;
import vip.mate.oauth.service.impl.UserDetailsServiceImpl;

/**
 * 安全配置中心
 *
 * @author xuzhanfu
 * @date 2019-10-11 23:25
 **/
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final IgnoreUrlPropsConfig ignoreUrlPropsConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 必须要定义，否则不支持grant_type=password模式
     * @return
     */
    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config
                = http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests();
        ignoreUrlPropsConfig.getUrls().forEach(e -> {
            config.antMatchers(e).permitAll();
        });
        config
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/v2/api-docs-ext").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }
}
