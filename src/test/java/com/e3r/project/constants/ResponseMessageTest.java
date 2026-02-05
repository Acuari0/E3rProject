package com.e3r.project.constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ResponseMessageTest {

    @Test
    void testMessageValues() {
        assertThat(ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE)
            .isEqualTo("exception.request_internal_server_error");
            
        assertThat(ResponseMessage.FIELD_REQUIRED)
            .isEqualTo("request.value_required");
            
        assertThat(ResponseMessage.REQUEST_INVALID_VALUE)
            .isEqualTo("request.invalid_value");
    }

    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException {
        Constructor<ResponseMessage> constructor = ResponseMessage.class.getDeclaredConstructor();
        
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, 
            constructor::newInstance);

        assertThat(exception.getCause())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Constants class");
    }
}
