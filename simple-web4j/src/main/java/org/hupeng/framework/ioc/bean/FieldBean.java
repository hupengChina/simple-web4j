package org.hupeng.framework.ioc.bean;

import java.lang.reflect.Field;

/**
 * @Author: hupeng
 * @since: 2019/8/4
 */
public class FieldBean {

    private Field field;

    private DefaultBean value;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public DefaultBean getValue() {
        return value;
    }

    public void setValue(DefaultBean value) {
        this.value = value;
    }

}
