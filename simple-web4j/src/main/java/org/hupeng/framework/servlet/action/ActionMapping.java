package org.hupeng.framework.servlet.action;

import org.hupeng.framework.ioc.Annotated.Action;
import org.hupeng.framework.ioc.Annotated.Controller;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionMapping {

    /**
     * action-Controller
     */
    private final static Map<String, String> actionReferences = new ConcurrentHashMap<>();

    /**
     * controller-actionMethod
     */
    private final static Map<String, ActionInfo> actionMethodMap = new ConcurrentHashMap<>();

    public static void init(Collection<Class<?>> classes){
        for (Class clazz: classes) {

            if(clazz.isAnnotationPresent(Controller.class)){
                String className = clazz.getSimpleName();
                Method[] methods = clazz.getMethods();
                for (Method method: methods) {
                    Action action = method.getAnnotation(Action.class);
                    if(action != null){
                        String requestPath = action.value();
                        actionReferences.put(requestPath,className);
                        ActionInfo actionInfo = new ActionInfo(clazz,method);
                        actionMethodMap.put(className, actionInfo);
                    }
                }

            }
        }
    }

    public static ActionInfo getAction(String requestPath) {
        String controllerName = actionReferences.get(requestPath);
        if(controllerName != null){
            return actionMethodMap.get(controllerName);
        }
        return null;
    }
}
