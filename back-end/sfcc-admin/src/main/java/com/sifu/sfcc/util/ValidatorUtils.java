/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.sifu.sfcc.util;


import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.sifu.sfcc.common.exception.ApiException;
import org.apache.commons.collections.ListUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws ApiException  校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws ApiException, IllegalAccessException {

        Field[] fields = object.getClass().getDeclaredFields();

        if (fields.length != 0) {
            String errorMessage = Strings.EMPTY;
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(NotBlank.class)) {
                    NotBlank notBlank = field.getAnnotation(NotBlank.class);
                    field.setAccessible(true);
                    if (field.get(object) instanceof Integer && ObjectUtils.isEmpty(object)) {
                        errorMessage = errorMessage.concat(notBlank.message());
                    }
                    if (field.get(object) instanceof String && StringUtils.isEmpty(field.get(object))) {
                        errorMessage = errorMessage.concat(notBlank.message());
                    }
                }

            }
            if (!StringUtils.isEmpty(errorMessage)) {
                throw new ApiException(errorMessage);
            }

        }

    }
}
