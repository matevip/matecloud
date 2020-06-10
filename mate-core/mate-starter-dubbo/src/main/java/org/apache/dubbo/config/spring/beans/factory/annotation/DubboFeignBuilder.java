package org.apache.dubbo.config.spring.beans.factory.annotation;

import feign.Feign;
import feign.Target;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ReflectionUtils;

import static com.alibaba.spring.util.AnnotationUtils.getAttributes;
import static org.springframework.core.annotation.AnnotationAttributes.fromMap;

@Slf4j
public class DubboFeignBuilder extends Feign.Builder {
    @Autowired
    private ApplicationContext applicationContext;

    public Reference defaultReference;

    final class DefaultReferenceClass{
        @Reference(check = false)
        String field;
    }

    public DubboFeignBuilder() {
        this.defaultReference = ReflectionUtils.findField(DefaultReferenceClass.class,"field").getAnnotation(Reference.class);
    }


    @Override
    public <T> T target(Target<T> target) {
        ReferenceBeanBuilder beanBuilder = ReferenceBeanBuilder.create(fromMap(getAttributes(defaultReference,
                applicationContext.getEnvironment(), true)), applicationContext).interfaceClass(target.type());
        try {
            T object = (T) beanBuilder.build().getObject();
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
