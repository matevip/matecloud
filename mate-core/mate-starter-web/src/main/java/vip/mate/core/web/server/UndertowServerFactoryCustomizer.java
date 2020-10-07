package vip.mate.core.web.server;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

/**
 * 解决undertow警告Buffer pool was not set on WebSocketDeploymentInfo
 *
 * @author pangu
 */
public class UndertowServerFactoryCustomizer implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

	@Override
	public void customize(UndertowServletWebServerFactory factory) {
		factory.addDeploymentInfoCustomizers(deploymentInfo -> {
			WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
			webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
			deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
		});
	}
}
