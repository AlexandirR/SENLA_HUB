package com.senla.configurations;

import configuration.Configuration;
import org.reflections.Reflections;
import com.senla.repository.interfacedata.Bibliography;
import com.senla.repository.interfacedata.OrderHistory;
import com.senla.repository.interfacedata.QuerySet;
import com.senla.repository.jsondata.BibliographyJson;
import com.senla.repository.jsondata.OrderHistoryJson;
import com.senla.repository.jsondata.QuerySetJson;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaConfiguration implements Configuration {

    private final String packageToScan = "com.senla";
    private Reflections scanner;
    private final Map<Class, Class> interfaceToImplementation;

    public JavaConfiguration() {
        scanner = new Reflections(packageToScan);
        interfaceToImplementation = new HashMap<>(Map.of(
                Bibliography.class, BibliographyJson.class,
                OrderHistory.class, OrderHistoryJson.class,
                QuerySet.class, QuerySetJson.class
        ));
    }

    @Override
    public String getPackageToScan() {
        return packageToScan;
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        return interfaceToImplementation.computeIfAbsent(interfaceClass, clazz -> {
            Set<Class<? extends T>> implClasses = scanner.getSubTypesOf(interfaceClass);
            if (implClasses.size() != 1) {
                throw new RuntimeException("Interface has 0 or more then 1 impl");
            }

            return implClasses.stream().findFirst().get();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
