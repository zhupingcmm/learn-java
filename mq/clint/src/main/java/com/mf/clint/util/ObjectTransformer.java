package com.mf.clint.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ObjectTransformer {

    public static <T> T transform (Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException  e) {
            throw new RuntimeException(e);
        }

        return target;
    }

    public static <T> List transform(List<?> list, Class<T> t){
        if (null == list) {
            list = new ArrayList<>();
        }

        List<T> result = new ArrayList<>();

        for (Object o : list) {
            result.add(transform(o, t));
        }

        return result;
    }
}
