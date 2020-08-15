package vip.mate.core.web.util;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

/**
 * RestTemplate Http请求封装
 *
 * @author pangu
 */
public class RestTemplateUtil {

    /**
     * get请求（超时设置）
     *
     * @param url
     * @return
     */
    public static Map httpGetRequestFactoryToMap(String url) {
        //超时处理设置
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);
        //应用超时设置
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate.getForObject(url, Map.class);
    }

    /**
     * get请求（无超时设置）
     *
     * @param url
     */
    public static Map restTemplateGetToMap(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }

    /**
     * get请求 并接收封装成string
     *
     * @param url
     */
    public static String restTemplateGetToStr(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }


    /**
     * get请求 并添加消息头
     */
    public static ResponseEntity<String> httpGetHeaders(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    /**
     * post请求 添加请求参数以及消息头
     *
     * @param url
     * @param map
     */
    public static String getCommonPolicyJson(String url, MultiValueMap<String, String> map, MediaType type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        //设置header
        headers.setContentType(type);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        //执行请求
        ResponseEntity<String> resp = restTemplate.postForEntity(url, request, String.class);
        return resp.getBody();
    }
}
