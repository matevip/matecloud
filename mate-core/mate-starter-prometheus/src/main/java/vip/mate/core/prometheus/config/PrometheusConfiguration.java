package vip.mate.core.prometheus.config;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Prometheus配置
 *
 * @author pangu
 */
@Slf4j
@Configuration
@ConditionalOnClass({MeterRegistry.class})
public class PrometheusConfiguration implements CommandLineRunner {

	@Value("${spring.application.name}")
	private String applicationName;

	@Bean
	MeterRegistryCustomizer<MeterRegistry> appMetricsCommonTags() {
		return registry -> registry.config().commonTags("application", applicationName);
	}

	@Override
	public void run(String... args) {
		log.info("matecloud prometheus startup successfully! ");
	}
}
