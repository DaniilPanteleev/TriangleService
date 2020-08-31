package entities.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionEntity {

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String message;

    @JsonProperty("exception")
    private String exception;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }

    public static final class ExceptionEntityBuilder {
        private String error;
        private String message;
        private String exception;

        private ExceptionEntityBuilder() {
        }

        public static ExceptionEntityBuilder anExceptionEntity() {
            return new ExceptionEntityBuilder();
        }

        public ExceptionEntityBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public ExceptionEntityBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ExceptionEntityBuilder withException(String exception) {
            this.exception = exception;
            return this;
        }

        public ExceptionEntity build() {
            ExceptionEntity exceptionEntity = new ExceptionEntity();
            exceptionEntity.error = this.error;
            exceptionEntity.message = this.message;
            exceptionEntity.exception = this.exception;
            return exceptionEntity;
        }
    }

}
