package context;

import annitations.Prototype;
import configuration.Configuration;
import factory.BeanFactory;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    @Setter
    private BeanFactory beanFactory;
    private final Map<Class, Object> beanMap = new ConcurrentHashMap<>();
    private Configuration config;

    @SneakyThrows
    public ApplicationContext(Configuration config) {
        this.config = config;
    }

    public Configuration getConfig() {
        return config;
    }

    public <T> T getBean(Class<T> clazz) {
        if (beanMap.containsKey(clazz)) {
            return (T) beanMap.get(clazz);
        }

        Class<? extends T> implClass = clazz;
        if (clazz.isInterface()) {
            implClass = config.getImplementationClass(clazz);
        }
        T t = beanFactory.getBean(implClass);

        if (clazz.getAnnotation(Prototype.class) != null) {
            beanMap.put(clazz, t);
        }

        return t;
    }
}
