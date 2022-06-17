package factory;

import annitations.PostConstruct;
import annotationconfigurator.AnnotationConfigurator;
import context.ApplicationContext;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanFactory {


    private List<AnnotationConfigurator> configurators = new ArrayList<>();
    private ApplicationContext applicationContext;
    private Reflections scanner;
    private String packageToScan = "annotationconfigurator";

    @SneakyThrows
    public BeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        scanner = new Reflections(packageToScan);
        for (Class<? extends AnnotationConfigurator> aClass : scanner.getSubTypesOf(AnnotationConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T getBean(Class<T> implClass) {
        T t = create(implClass);
        configure(t);
        invokeInit(implClass, t);
        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(Configurator -> Configurator.configure(t, applicationContext));
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
