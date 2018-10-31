package com.nbohn.pundit.rest.model.entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Entity Bean Utility.
 */
public final class EntityBeanUtil {

    /**
     * Constructor
     */
    private EntityBeanUtil() {
        // No access.
    }

    /**
     * Get the field in this bean class that is annotated with the specified annotation and make it accessible.
     * @param entity Entity being searched.
     * @param annotationClass Annotation class being sought.
     * @return Field annotated with the annotation or null if not found.
     */
    public static Field getAnnotatedField(final Object entity, final Class annotationClass) {
        Field annotatedField = null;
        final Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                if (fieldAnnotation.annotationType().equals(annotationClass)) {
                    annotatedField = field;
                    annotatedField.setAccessible(true);
                    break;
                }
            }
            if (annotatedField != null) {
                break;
            }
        }
        return annotatedField;
    }


    /**
     * Get the value of the field in this bean class that is annotated with the specified annotation.
     * @param entity Entity being searched.
     * @param annotationClass Annotation being sought class.
     * @return Value of the field with the sought annotation or null.
     * @throws IllegalAccessException Unable to access the annotated field.
     */
    public static Object getAnnotatedFieldValue(final Object entity, final Class annotationClass) throws IllegalAccessException {
        Object value = null;
        final Field field = getAnnotatedField(entity, annotationClass);
        if (field != null) {
            value = field.get(entity);
        }
        return value;
    }

}
