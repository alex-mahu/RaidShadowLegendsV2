package raidClicker;

import java.awt.*;
import java.util.Arrays;

public final class ColorMatrixGenerator {
    private static final int MATRIX_SIZE = 9;
    private final Robot robot;
    private final Point initialLocation;
    private Color[][] focusedSpot;
    private Color[][] unfocusedSpot;

    public ColorMatrixGenerator(Point initialLocation) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Robot Not initialized");
        }
        this.initialLocation = initialLocation;
    }

    public void takeFocusedPixelMatrix() {
        focusedSpot = getPixelMatrix();
    }

    public void takeUnfocusedPixelMatrix() {
        unfocusedSpot = getPixelMatrix();
    }

    public Color[][] getPixelMatrix() {
        final int startPointX = initialLocation.x - MATRIX_SIZE / 2;
        final int startPointy = initialLocation.y + MATRIX_SIZE / 2;
        int pointX = startPointX;
        int pointY = startPointy;
        Color[][] matrix = new Color[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = robot.getPixelColor(pointX, pointY);
                pointX++;
            }
            pointX = startPointX;
            pointY--;
        }
        return matrix;
    }

    public boolean isOneOfThePixelMatrixesVisible() {
        Color[][] currentMatrix = getPixelMatrix();
        return Arrays.deepEquals(focusedSpot, currentMatrix) || Arrays.deepEquals(unfocusedSpot, currentMatrix);
    }
}
