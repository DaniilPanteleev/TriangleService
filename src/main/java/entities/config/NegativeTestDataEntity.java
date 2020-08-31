package entities.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.dto.creation.CreateTriangleDto;
import entities.enums.TriangleServiceExceptionNames;

public class NegativeTestDataEntity {

    @JsonProperty("title")
    private String title;

    @JsonProperty("id")
    private String id;

    @JsonProperty("expectedStatusCode")
    private Integer statusCode;

    @JsonProperty("isRequestSpecExist")
    private Boolean isRequestSpecExist;

    @JsonProperty("isTriangleStoreFull")
    private Boolean isTriangleStoreFull;

    @JsonProperty("expectedError")
    private TriangleServiceExceptionNames exception;

    @JsonProperty("triangle")
    private CreateTriangleDto triangle;

    public Boolean getTriangleStoreFull() {
        return isTriangleStoreFull;
    }

    public CreateTriangleDto getTriangle() {
        return triangle;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Boolean getRequestSpecExist() {
        return isRequestSpecExist;
    }

    public TriangleServiceExceptionNames getException() {
        return exception;
    }

    public static final class NegativeTestDataEntityBuilder {
        private String title;
        private String id;
        private Integer statusCode;
        private Boolean isRequestSpecExist;
        private Boolean isTriangleStoreFull;
        private TriangleServiceExceptionNames exception;
        private CreateTriangleDto triangle;

        private NegativeTestDataEntityBuilder() {
        }

        public static NegativeTestDataEntityBuilder from(NegativeTestDataEntity negativeTestDataEntity) {
            return aNegativeTestDataEntity()
                    .withId(negativeTestDataEntity.id)
                    .withException(negativeTestDataEntity.exception)
                    .withIsRequestSpecExist(negativeTestDataEntity.isRequestSpecExist)
                    .withIsTriangleStoreFull(negativeTestDataEntity.isTriangleStoreFull)
                    .withStatusCode(negativeTestDataEntity.statusCode)
                    .withTitle(negativeTestDataEntity.title)
                    .withTriangle(negativeTestDataEntity.triangle);
        }

        public static NegativeTestDataEntityBuilder aNegativeTestDataEntity() {
            return new NegativeTestDataEntityBuilder();
        }

        public NegativeTestDataEntityBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public NegativeTestDataEntityBuilder withStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public NegativeTestDataEntityBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public NegativeTestDataEntityBuilder withIsRequestSpecExist(Boolean isRequestSpecExist) {
            this.isRequestSpecExist = isRequestSpecExist;
            return this;
        }

        public NegativeTestDataEntityBuilder withIsTriangleStoreFull(Boolean isTriangleStoreFull) {
            this.isTriangleStoreFull = isTriangleStoreFull;
            return this;
        }

        public NegativeTestDataEntityBuilder withException(TriangleServiceExceptionNames exception) {
            this.exception = exception;
            return this;
        }

        public NegativeTestDataEntityBuilder withTriangle(CreateTriangleDto triangle) {
            this.triangle = triangle;
            return this;
        }

        public NegativeTestDataEntity build() {
            NegativeTestDataEntity negativeTestDataEntity = new NegativeTestDataEntity();
            negativeTestDataEntity.id = this.id;
            negativeTestDataEntity.triangle = this.triangle;
            negativeTestDataEntity.isRequestSpecExist = this.isRequestSpecExist;
            negativeTestDataEntity.isTriangleStoreFull = this.isTriangleStoreFull;
            negativeTestDataEntity.title = this.title;
            negativeTestDataEntity.exception = this.exception;
            negativeTestDataEntity.statusCode = this.statusCode;
            return negativeTestDataEntity;
        }
    }

    @Override
    public String toString() {
        return "NegativeTestDataEntity{" +
                "title='" + title + '\'' +
                ", statusCode=" + statusCode +
                ", isRequestSpecExist=" + isRequestSpecExist +
                ", isTriangleStoreFull=" + isTriangleStoreFull +
                ", exception=" + exception +
                ", triangle=" + triangle +
                '}';
    }
}
