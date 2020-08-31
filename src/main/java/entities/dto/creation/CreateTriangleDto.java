package entities.dto.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.dto.BaseBody;

public class CreateTriangleDto extends BaseBody {

    @JsonProperty("separator")
    private String separator;

    @JsonProperty("input")
    private String input;

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public static final class CreateTriangleDtoBuilder {
        private String separator;
        private String input;

        private CreateTriangleDtoBuilder() {
        }

        public static CreateTriangleDtoBuilder aCreateTriangleDto() {
            return new CreateTriangleDtoBuilder();
        }

        public CreateTriangleDtoBuilder withSeparator(String separator) {
            this.separator = separator;
            return this;
        }

        public CreateTriangleDtoBuilder withInput(String input) {
            this.input = input;
            return this;
        }

        public CreateTriangleDto build() {
            CreateTriangleDto createTriangleDto = new CreateTriangleDto();
            createTriangleDto.setSeparator(separator);
            createTriangleDto.setInput(input);
            return createTriangleDto;
        }
    }

    @Override
    public String toString() {
        return "CreateTriangleDto{" +
                "separator='" + separator + '\'' +
                ", input='" + input + '\'' +
                '}';
    }
}
