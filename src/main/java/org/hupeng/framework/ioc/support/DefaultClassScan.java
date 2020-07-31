package org.hupeng.framework.ioc.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.ioc.Annotated.Bean;
import org.hupeng.framework.util.ClassUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Slf4j
public class DefaultClassScan {

    public Collection<Class<?>> scan(String packageName){
        Collection<Class<?>> classes = new HashSet<>();
        try {
            getClass(classes,packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private void getClass(Collection<Class<?>> classes, String packageName) throws IOException {

        Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace(".","/"));

        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String protocol = url.getProtocol();
            if("file".equals(protocol)){
                String uri = url.getPath().replaceAll("%20", " ");
                addClasses(classes,packageName,uri);
            }
        }
    }

    private void addClasses(Collection<Class<?>> classes, String packageName, String uri) throws IOException {

        File[] files = new File(uri).listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());

        for(File file: files){
            String fileName = file.getName();
            if(file.isDirectory()) {
                getClass(classes,packageName+"."+fileName);
            }
            else {
                try {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    if(StringUtils.isNoneEmpty(packageName)){
                        className = packageName + "." + className;
                    }
                    Class<?> clazz = ClassUtil.loadClass(className, false);
                    if(checkClass(clazz)) {
                        classes.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                } catch (Throwable e){
                    log.error(e.getMessage());
                }
            }
        }
    }

    boolean checkClass(Class clazz){
        return clazz.isAnnotationPresent(Bean.class);
    };
}
