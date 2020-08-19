package org.hupeng.framework.context.bean;

import java.lang.reflect.Field;

/**
 * @Author: hupeng
 * @since: 2019/8/4
 */
public class FieldBean {

    private Field field;

    private BeanDefinition value;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public BeanDefinition getValue() {
        return value;
    }

    public void setValue(BeanDefinition value) {
        this.value = value;
    }

}
