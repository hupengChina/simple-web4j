package org.hupeng.framework.commons.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * @author : hupeng
 * @date : 2020/8/20
 */
public class ColorConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        int level = event.getLevel().toInt();
        switch(level) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
                return ANSIConstants.DEFAULT_FG;
            case Level.DEBUG_INT:
                return ANSIConstants.BLUE_FG;
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }
}
