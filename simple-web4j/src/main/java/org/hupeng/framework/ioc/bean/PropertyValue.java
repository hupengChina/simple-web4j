package org.hupeng.framework.ioc.bean;

import java.lang.reflect.Field;

/**
 * @author : hupeng
 * @date : 2020/8/17
 */
public class PropertyValue {

    private Field field;

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public PropertyValue(Field field) {
        this.field = field;
    }

}
