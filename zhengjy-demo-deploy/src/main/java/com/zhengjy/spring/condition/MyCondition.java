package com.zhengjy.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by zhengjy on 2017/7/13.
 */
public class MyCondition implements Condition
{
    /**
     * 这里写自己的逻辑，只有返回true，才会启用配置
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
    {
        return true;
    }
}
