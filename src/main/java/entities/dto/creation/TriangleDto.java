package entities.dto.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.dto.BaseBody;

public class TriangleDto extends BaseBody {

    @JsonProperty("id")
    private String id;

    @JsonProperty("firstSide")
    private Double firstSide;

    @JsonProperty("secondSide")
    private Double secondSide;

    @JsonProperty("thirdSide")
    private Double thirdSide;

    public String getId() {
        return id;
    }

    public Double getFirstSide() {
        return firstSide;
    }

    public Double getSecondSide() {
        return secondSide;
    }

    public Double getThirdSide() {
        return thirdSide;
    }

    public static final class TriangleDtoBuilder {
        private String id;
        private Double firstSide;
        private Double secondSide;
        private Double thirdSide;

        private TriangleDtoBuilder() {
        }

        public static TriangleDtoBuilder aTriangleDto() {
            return new TriangleDtoBuilder();
        }

        public TriangleDtoBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public TriangleDtoBuilder withFirstSide(Double firstSide) {
            this.firstSide = firstSide;
            return this;
        }

        public TriangleDtoBuilder withSecondSide(Double secondSide) {
            this.secondSide = secondSide;
            return this;
        }

        public TriangleDtoBuilder withThirdSide(Double thirdSide) {
            this.thirdSide = thirdSide;
            return this;
        }

        public TriangleDto build() {
            TriangleDto triangleDto = new TriangleDto();
            triangleDto.secondSide = this.secondSide;
            triangleDto.firstSide = this.firstSide;
            triangleDto.id = this.id;
            triangleDto.thirdSide = this.thirdSide;
            return triangleDto;
        }
    }

    @Override
    public String toString() {
        return "TriangleDto{" +
                "firstSide=" + firstSide +
                ", secondSide=" + secondSide +
                ", thirdSide=" + thirdSide +
                '}';
    }
}
