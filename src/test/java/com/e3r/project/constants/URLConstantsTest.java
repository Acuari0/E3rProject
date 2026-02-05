package com.e3r.project.constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class URLConstantsTest {

    @Test
    void testURLValues() {
        assertAll("Verificación de constantes de URL",
            () -> assertEquals("/api/v1", URLConstants.API_BASE),
            () -> assertEquals("/api/v1/prices", URLConstants.API_PRICES),
            () -> assertEquals("/find", URLConstants.FIND)
        );
    }

    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException {
        Constructor<URLConstants> constructor = URLConstants.class.getDeclaredConstructor();
        
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("URLConstants class", exception.getCause().getMessage());
    }
}
