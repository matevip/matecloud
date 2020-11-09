package vip.mate.core.encrypt.config;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import vip.mate.core.encrypt.annotation.SignEncrypt;
import vip.mate.core.encrypt.handler.SignEncryptHandler;
import vip.mate.core.encrypt.handler.impl.SignEncryptHandlerImpl;
import vip.mate.core.encrypt.interceptor.SignEncryptInterceptor;

/**
 * 签名加密配置
 *
 * @author gaoyang
 */
@ConditionalOnExpression(value = "environment.getProperty('encrypt.signSecret')!=null && " +
		"environment.getProperty('encrypt.signSecret').trim()!=''")
public class SignEncryptConfiguration {

	@ConditionalOnMissingBean(SignEncryptHandler.class)
	@Bean
	public SignEncryptHandler sortSignEncryptHandlerDefult() {
		return new SignEncryptHandlerImpl();
	}

	@Autowired
	private SignEncryptHandler signEncryptHandler;


	@Bean
	public DefaultPointcutAdvisor sortSignEncryptAdvisor(@Value("${encrypt.signSecret}") String sortSignSecret) {
		SignEncryptInterceptor interceptor = new SignEncryptInterceptor(sortSignSecret, signEncryptHandler);
		AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(null, SignEncrypt.class);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(pointcut);
		advisor.setAdvice(interceptor);
		return advisor;
	}
}
