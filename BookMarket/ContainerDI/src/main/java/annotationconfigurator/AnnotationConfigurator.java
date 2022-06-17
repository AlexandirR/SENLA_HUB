package annotationconfigurator;

import context.ApplicationContext;

public interface AnnotationConfigurator {

    void configure(Object t, ApplicationContext context);
}
