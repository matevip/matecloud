package vip.mate.core.encrypt.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 请求缓存包装器
 *
 * @author gaoyang
 */
public class CacheRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;


	public byte[] getBody() {
		return body;
	}

	public CacheRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		ServletInputStream inputStream = request.getInputStream();
		String header = request.getHeader("Content-Length");
		if (header == null) {
			return;
		}
		int contentLength = Integer.valueOf(header);
		byte[] bytes = new byte[contentLength];
		int readCount = 0;
		while (readCount < contentLength) {
			readCount += inputStream.read(bytes, readCount, contentLength - readCount);
		}
		body = bytes;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
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
