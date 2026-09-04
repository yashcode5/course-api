package org.main.exceptions;

public record ErrorResponse(
        String code,
        String message
) {
}
