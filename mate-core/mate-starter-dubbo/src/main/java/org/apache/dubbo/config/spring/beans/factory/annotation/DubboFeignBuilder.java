package org.apache.dubbo.config.spring.beans.factory.annotation;

import feign.Feign;
import feign.Target;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.reference.ReferenceBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.util.Objects;

/**
 * Dubbo、Feign整合类
 *
 * @author pangu
 */
@Slf4j
public class DubboFeignBuilder extends Feign.Builder {

    @Autowired
    @SuppressWarnings("all")
    private ApplicationContext applicationContext;

    public DubboReference defaultReference;

    static final class DefaultReferenceClass {
        @DubboReference(check = false)
        String field;
    }

    public DubboFeignBuilder() {
        this.defaultReference = Objects.requireNonNull(ReflectionUtils.findField(DefaultReferenceClass.class, "field")).getAnnotation(DubboReference.class);
    }


    @Override
    public <T> T target(Target<T> target) {
        ReferenceBeanBuilder referenceBeanBuilder = new ReferenceBeanBuilder();
        referenceBeanBuilder.setInterface(target.type());
        try {
            T object = (T) referenceBeanBuilder.build().getObject();
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
