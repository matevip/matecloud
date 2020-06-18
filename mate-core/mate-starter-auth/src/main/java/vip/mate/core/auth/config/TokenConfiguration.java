package vip.mate.core.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import vip.mate.core.auth.advice.TokenRequestBodyAdvice;
import vip.mate.core.auth.aspect.TokenAspect;
import vip.mate.core.auth.props.TokenProperties;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(TokenProperties.class)
@ConditionalOnProperty(value = TokenProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class TokenConfiguration {
    private final HttpServletRequest request;

    @Bean
    public RequestBodyAdvice requestBodyAdvice() {
        return new TokenRequestBodyAdvice(request);
    }


}
