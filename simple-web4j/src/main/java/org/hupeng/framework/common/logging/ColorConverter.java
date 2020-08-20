package org.hupeng.framework.common.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;

/**
 * @author : hupeng
 * @date : 2020/8/20
 */
public class ColorConverter extends CompositeConverter<ILoggingEvent> {
    @Override
    protected String transform(ILoggingEvent event, String in) {
        return null;
    }
}
