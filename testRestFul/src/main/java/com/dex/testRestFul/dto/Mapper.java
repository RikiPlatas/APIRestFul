package com.dex.testRestFul.dto;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
@Component
public class Mapper<T, U> {
    public U map(T source, Class<U> targetClass) throws IllegalAccessException, InstantiationException {
        U target = targetClass.newInstance();

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (sourceField.getName().equals(targetField.getName())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    targetField.set(target, sourceField.get(source));
                    break;
                }
            }
        }

        return target;
    }
}
