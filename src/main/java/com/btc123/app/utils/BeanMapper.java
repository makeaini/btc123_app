package com.btc123.app.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * Created by shining on 2018/5/15.
 */
public class BeanMapper {

    private static DozerBeanMapper dozer = new DozerBeanMapper();


    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }


    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }


    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}
