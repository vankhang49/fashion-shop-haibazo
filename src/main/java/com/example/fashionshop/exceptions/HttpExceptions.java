package com.example.fashionshop.exceptions;

public class HttpExceptions {

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    /**
     * Exception for HTTP 403 Forbidden error.
     * Client has been authenticated but does not have access rights
     * to the requested resource.
     */
    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message) {
            super(message);
        }
    }

    /**
     * Exception for HTTP 404 Not Found error.
     * The requested resource does not exist on the server.
     */
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception for HTTP error 405 Method Not Allowed.
     * HTTP method used in the request (e.g. GET, POST, PUT)
     * is not supported for the requested resource.
     */
    public static class MethodNotAllowedException extends RuntimeException {
        public MethodNotAllowedException(String message) {
            super(message);
        }
    }

    /**
     * Exception for HTTP 408 Request Timeout error.
     * The server took too long to respond to the client's request,
     * timeout limit exceeded.
     */
    public static class RequestTimeoutException extends RuntimeException {
        public RequestTimeoutException(String message) {
            super(message);
        }
    }

    /**
     * Exception for HTTP 500 Internal Server Error.
     * An unexpected error occurred on the server,
     * prevents the server from fulfilling the request.
     */
    public static class InternalServerErrorException extends RuntimeException {
        public InternalServerErrorException(String message) {
            super(message);
        }
    }

    /**
     * Exception for HTTP 503 Service Unavailable error.
     * The server is currently unavailable or overloaded and cannot process the request.
     */
    public static class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String message) {
            super(message);
        }
    }
    /**
     * Exception for incorrect format error.
     * For example, the data format is inconsistent.
     */
    public static class FormatException extends RuntimeException {
        public FormatException(String message) {
            super(message);
        }
    }

    /**
     * Exception for incorrect Number error.
     */
    public static class NumberFormatException extends RuntimeException {
        public NumberFormatException(String message) {
            super(message);
        }
    }

    /**
     * Exception for NullPointerException error.
     * Thrown when a null reference is used.
     */
    public static class NullPointerException extends RuntimeException {
        public NullPointerException(String message) {
            super(message);
        }
    }
}
