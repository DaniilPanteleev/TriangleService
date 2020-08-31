package utils;

import entities.dto.creation.TriangleDto;

public final class TriangleUtils {

    private TriangleUtils() {
    }

    public static double calculatePerimeter(TriangleDto triangle) {
        return triangle.getFirstSide() + triangle.getSecondSide() + triangle.getThirdSide();
    }

    public static double calculateArea(TriangleDto triangle) {
        Double halfP = (triangle.getFirstSide() + triangle.getSecondSide() + triangle.getThirdSide()) / 2;
        return Math.sqrt(halfP.doubleValue() * (halfP.doubleValue() - triangle.getFirstSide()) *
                (halfP.doubleValue() - triangle.getSecondSide()) * (halfP.doubleValue() - triangle.getThirdSide()));
    }

}
