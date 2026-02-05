package com.e3r.project.constants;

public final class ResponseMessage {

    private ResponseMessage() {
        throw new IllegalStateException("Constants class");
    }

    // SERVER
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "exception.request_internal_server_error";

    // REQUEST
    public static final String FIELD_REQUIRED = "request.value_required";
    public static final String REQUEST_INVALID_VALUE = "request.invalid_value";
}
