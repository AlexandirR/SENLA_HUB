package annotationconfigurator;

import annitations.Inject;
import context.ApplicationContext;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectConfigurator implements AnnotationConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object bean = context.getBean(field.getType());
                field.set(t, bean);
            }
        }
    }
}
