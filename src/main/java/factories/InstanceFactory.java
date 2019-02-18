package factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InstanceFactory is a factory of default instances. These are created the first time and they are retrieved
 * when needed. The Factory is general but creates (and stores) concrete implementations as Singleton.
 * Largely used for the Models.
 *
 * <strong>WARNING:</strong> should not be used with the current DatabaseWrapper.
 */
public class InstanceFactory {
    private static final Map<String, Object> instances = new HashMap<>();

    /**
     * Private, the Factory has only static fields and methods.
     */
    private InstanceFactory() {}

    /**
     * Returns a base instance of the type T of the class klass. This method permit the simplified construction
     * of objects that don't have a without-arguments constructor. An example of that are the Models that need
     * the fields set at construction with valid values.
     * Note that the object returned should not be used with functions that rely on their fields because,
     * of course, they are null or invalid.
     *
     * <strong>Note:</strong> the current implementation caches the objects already created, so if this
     * method is called again with the same class then the same object of the last time is returned.
     *
     * @param klass The class from witch construct the object with that type. Example:
     *              <code>ArticleModel.class</code>.
     * @param <T> Type of the class of the returned object.
     * @return An object of the class klass.
     */
    public static <T> T getInstance(final Class<T> klass) {
        final String klassName = klass.getName();
        final Object obj;

        if (instances.containsKey(klassName)) {
            obj = instances.get(klassName);
        } else {
            obj = createNewInstance(klassName);
        }

        return klass.cast(obj);
    }

    /**
     * Create and return a new object with the class retrieved with klassName.
     *
     * @param klassName A valid class name.
     * @return A new object of class klass.
     */
    private static Object createNewInstance(final String klassName) {
        // Get the class from the name
        Class<?> c = null;
        try {
            c = Class.forName(klassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert (c != null);

        // Create an array of constructors for the class
        final Constructor<?>[] cons = c.getConstructors();

        // Create a list of parameters for the first constructor
        final Class<?>[] paramTypes = cons[0].getParameterTypes();

        // Set default values for every params
        final List<Object> params = createDefaultValueParams(paramTypes);

        // Create the new instance
        Object obj = null;
        try {
            obj = cons[0].newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // Save the instance in the Map
        instances.put(klassName, obj);

        // Return the instance
        return obj;
    }

    /**
     * Returns valid parameters to give to the constructor.
     *
     * @param paramTypes The type of the parameters.
     * @return A list of valid parameters.
     */
    private static List<Object> createDefaultValueParams(final Class<?>[] paramTypes) {
        final List<Object> params = new ArrayList<>();

        for (final Class<?> type : paramTypes) {
            params.add(type.isPrimitive() ? 0 : null);
        }

        return params;
    }
}
