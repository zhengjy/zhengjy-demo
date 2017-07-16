package com.zhengjy.spring.condition.t1;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by zhengjy on 2017/7/15.
 */
public class LinuxCondition implements Condition {

    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }

}
