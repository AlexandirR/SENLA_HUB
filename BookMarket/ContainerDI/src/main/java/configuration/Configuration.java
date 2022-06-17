package configuration;

import org.reflections.Reflections;

import java.util.Map;

public interface Configuration {

    String getPackageToScan();

    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

    Reflections getScanner();
}
