package com.e3r.project.constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PatternsTest {

    @Test
    void testDateTimePatternValue() {
        assertEquals("yyyy-MM-dd-HH.mm.ss", Patterns.DATE_TIME_PATTERN);
    }

    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException {
    
        Constructor<Patterns> constructor = Patterns.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertTrue(exception.getCause() instanceof IllegalStateException);
        assertEquals("Patterns class", exception.getCause().getMessage());
    }
}