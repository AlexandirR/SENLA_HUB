package application;

import configuration.Configuration;
import context.ApplicationContext;
import factory.BeanFactory;
import lombok.SneakyThrows;

public class Application {

    @SneakyThrows
    public static <T> ApplicationContext run(Class<? extends Configuration> configuration) {
        Configuration appConfig = configuration.getDeclaredConstructor().newInstance();
        ApplicationContext applicationContext = new ApplicationContext(appConfig);
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        return applicationContext;
    }
}
