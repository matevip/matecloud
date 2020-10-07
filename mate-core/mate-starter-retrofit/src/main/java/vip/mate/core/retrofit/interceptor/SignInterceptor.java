package vip.mate.core.retrofit.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import lombok.Setter;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 签名拦截器
 *
 * @author pangu
 */
@Setter
@Component
public class SignInterceptor extends BasePathMatchInterceptor {

	private String accessKeyId;

	private String accessKeySecret;

	@Override
	public Response doIntercept(Chain chain) throws IOException {
		Request request = chain.request();
		Request newReq = request.newBuilder()
				.addHeader("accessKeyId", accessKeyId)
				.addHeader("accessKeySecret", accessKeySecret)
				.build();
		return chain.proceed(newReq);
	}
}
