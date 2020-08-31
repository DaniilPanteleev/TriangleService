package entities.mothers;

import entities.config.ExceptionEntity;
import entities.enums.TriangleServiceExceptionNames;

import static java.lang.String.format;

public class TriangleExceptionMother {

    public static ExceptionEntity getExceptionEntity(TriangleServiceExceptionNames exceptionName) {
        switch (exceptionName) {
            case UNAUTHORIZED:
                return getUnauthorizedException();
            case LIMIT:
                return getLimitException();
            case UNPROCESSABLE:
                return getUnprocessableException();
            case NOT_FOUND:
                return getNotFoundException();
            default:
                throw new RuntimeException(format("Implementation for exception %s doesn't exist!", exceptionName));
        }
    }

    private static ExceptionEntity getLimitException() {
        return ExceptionEntity.ExceptionEntityBuilder.anExceptionEntity()
                .withError("Unprocessable Entity")
                .withMessage("Limit exceeded")
                .withException("com.natera.test.triangle.exception.LimitExceededException")
                .build();
    }

    private static ExceptionEntity getUnprocessableException() {
        return ExceptionEntity.ExceptionEntityBuilder.anExceptionEntity()
                .withError("Unprocessable Entity")
                .withMessage("Cannot process input")
                .withException("com.natera.test.triangle.exception.UnprocessableDataException")
                .build();
    }

    private static ExceptionEntity getNotFoundException() {
        String error = "Not Found";
        return ExceptionEntity.ExceptionEntityBuilder.anExceptionEntity()
                .withError(error)
                .withMessage(error)
                .withException("com.natera.test.triangle.exception.NotFounException")
                .build();
    }

    private static ExceptionEntity getUnauthorizedException() {
        return ExceptionEntity.ExceptionEntityBuilder.anExceptionEntity()
                .withError("Unauthorized")
                .withMessage("No message available")
                .build();
    }

}
