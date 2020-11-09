package vip.mate.core.encrypt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import vip.mate.core.encrypt.handler.EncryptHandler;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * 加密请求包装器
 *
 * @author gaoyang
 */
@Slf4j
public class EncryptRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;
	private EncryptHandler encryptHandler;

	public EncryptRequestWrapper(HttpServletRequest request, EncryptHandler encryptHandler) throws IOException, ServletException {
		super(request);
		this.encryptHandler = encryptHandler;
		if (!request.getContentType().toLowerCase().equals(MediaType.APPLICATION_JSON_VALUE) && !request.getContentType().toLowerCase().equals(MediaType.APPLICATION_JSON_UTF8_VALUE.toLowerCase())) {
			throw new ServletException("contentType error");
		}
		ServletInputStream inputStream = request.getInputStream();
		int contentLength = Integer.parseInt(request.getHeader("Content-Length"));
		byte[] bytes = new byte[contentLength];
		int readCount = 0;
		while (readCount < contentLength) {
			readCount += inputStream.read(bytes, readCount, contentLength - readCount);
		}
		body = bytes;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		log.info("接收到的请求密文：" + new String(body));
		byte[] decode = encryptHandler.decode(body);
		String urlDecodeStr = URLDecoder.decode(new String(decode), "UTF-8");
		log.info("解密后的报文：" + urlDecodeStr);
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(urlDecodeStr.getBytes());
		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return byteArrayInputStream.available() == 0;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
			}

			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
	}
}
