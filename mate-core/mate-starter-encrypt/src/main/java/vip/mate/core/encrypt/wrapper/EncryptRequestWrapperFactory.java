package vip.mate.core.encrypt.wrapper;

import org.springframework.http.MediaType;
import vip.mate.core.encrypt.handler.EncryptHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求加密包装器工厂
 *
 * @author gaoyang
 */
public class EncryptRequestWrapperFactory {

	public static HttpServletRequest getWrapper(HttpServletRequest request,
	                                            EncryptHandler encryptService) throws IOException, ServletException {
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		if (contentType == null || contentLength == 0) {
			return request;
		}
		contentType = contentType.toLowerCase();
		if (contentIsJson(contentType)) {
			return new EncryptBodyRequestWrapper(request, encryptService);
		}
		return request;
	}

	public static HttpServletRequest getCacheWarpper(HttpServletRequest request) throws IOException, ServletException {
		if (!"POST".equalsIgnoreCase(request.getMethod()) ||
				!contentIsJson(request.getContentType())) {
			return request;
		}
		return new CacheRequestWrapper(request);
	}

	public static boolean contentIsJson(String contentType) {
		return contentType.equals(MediaType.APPLICATION_JSON_VALUE.toLowerCase()) ||
				contentType.equals(MediaType.APPLICATION_JSON_UTF8_VALUE.toLowerCase());
	}
}
