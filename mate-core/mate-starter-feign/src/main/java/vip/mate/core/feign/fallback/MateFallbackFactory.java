package vip.mate.core.feign.fallback;

import feign.Target;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 默认fallback，减少必要的编写fallback类
 * @param <T>
 */
@AllArgsConstructor
public class MateFallbackFactory<T> implements FallbackFactory<T> {
    private final Target<T> target;

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new vip.mate.core.feign.fallback.MateFeignFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }
}
