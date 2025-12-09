package core.container;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DIContainer {
    private static final Map<Class<?>, Class<?>> CLASS_REGISTRY = new HashMap<>();
    private static final Map<Class<?>, Object> INSTANCE_CACHE = new HashMap<>();

    public static <T> void register(Class<T> abstractType, Class<? extends T> implementationType) {
        CLASS_REGISTRY.put(abstractType, implementationType);
    }

    public static <T> T resolve(Class<T> abstractType) {
        if (INSTANCE_CACHE.containsKey(abstractType)) {
            return (T) INSTANCE_CACHE.get(abstractType);
        }

        var implementationType = CLASS_REGISTRY.get(abstractType);
        if (implementationType == null) {
            throw new RuntimeException("No implementation registered for: " + abstractType.getName());
        }

        var instance = createInstance((Class<T>) implementationType);
        INSTANCE_CACHE.put(abstractType, instance);
        return instance;
    }

    private static <T> T createInstance(Class<T> implementationType) {
        try {
            var targetConstructor = findConstructorWithMostParameters(implementationType.getConstructors());

            var parameters = resolveParameters(targetConstructor);

            return (T) targetConstructor.newInstance(parameters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate: " + implementationType.getName(), e);
        }
    }


    private static Constructor<?> findConstructorWithMostParameters(Constructor<?>[] constructors) {
        var targetConstructor = constructors[0];
        for (var constructor : constructors) {
            if (constructor.getParameterCount() > targetConstructor.getParameterCount()) {
                targetConstructor = constructor;
            }
        }
        return targetConstructor;
    }

    private static Object[] resolveParameters(Constructor<?> constructor) {
        var parameterTypes = constructor.getParameterTypes();
        var parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = resolve(parameterTypes[i]);
        }
        return parameters;
    }
}

