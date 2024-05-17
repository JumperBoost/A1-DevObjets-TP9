package fr.umontpellier.iut;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class BaseTest {

    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(Object object, String name) {
        Class<?> c = object.getClass();
        while (c != null) {
            try {
                Field field = c.getDeclaredField(name);
                field.setAccessible(true);
                return (T) field.get(object);
            } catch (NoSuchFieldException e) {
                c = c.getSuperclass();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


    public static void setAttribute(Object object, String name, Object value) {
        Class<?> c = object.getClass();
        while (c != null) {
            try {
                Field field = c.getDeclaredField(name);
                field.setAccessible(true);
                field.set(object, value);
                return;
            } catch (NoSuchFieldException e) {
                c = c.getSuperclass();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return;
            }
        }
        throw new RuntimeException("No such field: " + name);
    }

    public static <T> boolean containsReference(Collection<T> collection, T element) {
        return collection.stream().anyMatch(e -> e == element);
    }

    @SafeVarargs
    public static <T> boolean containsReferences(Collection<T> collection, T... elements) {
        for (T element : elements) {
            if (collection.stream().noneMatch(e -> e == element)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean containsReferences(Collection<T> collection, Collection<T> collection2) {
        for (T element : collection2) {
            if (collection.stream().noneMatch(e -> e == element)) {
                return false;
            }
        }
        return true;
    }



    @SafeVarargs
    public static <T> boolean containsExactlyReferencesInOrder(List<T> list, T... elements) {
        if (elements.length != list.size()) {
            return false;
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != list.get(i)) {
                return false;
            }
        }
        return true;
    }


}
