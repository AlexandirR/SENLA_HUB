package annotationconfigurator;

import annitations.InjectByProperty;
import context.ApplicationContext;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

public class InjectByPropertyConfigurator implements AnnotationConfigurator {

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        Properties properties = new Properties();
        for (Field field : implClass.getDeclaredFields()) {
            InjectByProperty annotation = field.getAnnotation(InjectByProperty.class);
            if (annotation != null) {
                Path path = Paths.get(annotation.nameOfFile());
                if (Files.exists(path)) {
                    try (InputStream in = Files.newInputStream(path)) {
                        properties.load(in);
                    }
                }
                String value = annotation.nameOfProperty().isEmpty() ? properties.getProperty(field.getName()) : properties.getProperty(annotation.nameOfProperty());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }
}
