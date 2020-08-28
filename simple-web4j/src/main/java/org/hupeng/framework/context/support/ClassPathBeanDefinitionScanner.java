package org.hupeng.framework.context.support;

import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.commons.util.ClassUtil;
import org.hupeng.framework.context.annotation.*;
import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.bean.DefaultBeanDefinition;
import org.hupeng.framework.context.factory.BeanDefinitionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author : hupeng
 * @date : 2020/8/18
 */
public class ClassPathBeanDefinitionScanner {

    private static final Logger log = LoggerFactory.getLogger(ClassPathBeanDefinitionScanner.class);

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public int scan(String... basePackages) {
        int count = 0;
        for (String packageName: basePackages) {
            Collection<Class<?>> classes = doScan(packageName);
            for (Class beanClass: classes) {
                BeanDefinition bd = new DefaultBeanDefinition(beanClass);
                registry.registerBeanDefinition(bd.getName(),bd);
            }
            count +=classes.size();
        }
        return count;
    }

    public Collection<Class<?>> doScan(String packageName){
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
                String className = url.getPath().replaceAll("%20", " ");
                loadClass(classes,packageName,className);
            }
            if("jar".equals(protocol)){
                JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                if (jarURLConnection != null) {
                    JarFile jarFile = jarURLConnection.getJarFile();
                    if (jarFile != null) {
                        Enumeration<JarEntry> jarEnties = jarFile.entries();
                        while (jarEnties.hasMoreElements()) {
                            JarEntry jarEntry = jarEnties.nextElement();
                            String jarEntryName = jarEntry.getName();
                            if (jarEntryName.endsWith(".class")) {
                                String className = jarEntryName.substring(0,
                                        jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                addClass(classes, className);
                            }
                        }
                    }
                }
            }
        }
    }

    private void loadClass(Collection<Class<?>> classes, String packageName, String uri) throws IOException {

        File[] files = new File(uri).listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());

        for(File file: files){
            String fileName = file.getName();
            if(file.isDirectory()) {
                getClass(classes,packageName+"."+fileName);
            }
            else {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if(StringUtils.isNoneEmpty(packageName)){
                    className = packageName + "." + className;
                }
                addClass(classes, className);
            }
        }
    }

    public void addClass(Collection<Class<?>> classes, String className){
        try {
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

    boolean checkClass(Class clazz){
        return clazz.isAnnotationPresent(Controller.class)
                ||clazz.isAnnotationPresent(Service.class)
                ||clazz.isAnnotationPresent(Repository.class)
                ||clazz.isAnnotationPresent(Component.class)
                ||clazz.isAnnotationPresent(Configuration.class);
    };
}
