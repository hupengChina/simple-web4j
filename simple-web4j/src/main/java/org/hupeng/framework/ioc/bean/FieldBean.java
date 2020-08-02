package org.hupeng.framework.ioc.bean;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * @Author: hupeng
 * @since: 2019/8/4 15:53
 */
@Data
public class FieldBean {

    private Field field;

    private DefaultBean value;
}
