package raidClicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static java.awt.MouseInfo.getPointerInfo;

public final class MouseActions {

    private final Robot robot;
    private Point mouseLocation;
    private ColorMatrixGenerator colorMatrixGenerator;

    public MouseActions() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("No robot for you!");
        }
    }

    public void setLocation(Point mouseLocationFromUi) {
        mouseLocation = mouseLocationFromUi;
        takeColorMatrixes();
    }

    public Point getMouseLocation() {
        return mouseLocation;
    }

    public MouseActions takeMouseLocationFromScreen() {
        mouseLocation = getPointerInfo().getLocation();
        takeColorMatrixes();
        return this;
    }

    public MouseActions dumbDoubleClick() {
        return dumbDoubleClick(25);
    }

    public MouseActions dumbDoubleClick(int milliToClick) {
        moveToMouseLocation();
        mouseClick();
        Timer timer = new Timer(milliToClick, null);
        timer.addActionListener(e -> {
            mouseClick();
            timer.stop();
        });
        timer.start();

        return this;
    }

    public void click() {
        if (ApplicationStatus.isSmart()) {
            clickUntilButtonNotVisible();
        } else {
            dumbDoubleClick();
        }
    }

    public void clickUntilButtonNotVisible() {
        Point currentLocation = getPointerInfo().getLocation();
        int tries = 10;
        do {
            moveToMouseLocation();
            mouseClick();
            mouseClick();
            tries--;
        } while (colorMatrixGenerator.isOneOfThePixelMatrixesVisible() && tries > 0);
        moveToMouseLocation(currentLocation.x, currentLocation.y);
    }

    private void mouseClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void moveToMouseLocation() {
        moveToMouseLocation(mouseLocation.x, mouseLocation.y);
    }

    private void moveToMouseLocation(int x, int y) {
        robot.mouseMove(x, y);
    }

    public boolean shouldPerformClick() {
        return colorMatrixGenerator.isOneOfThePixelMatrixesVisible();
    }

    private void takeColorMatrixes() {
        colorMatrixGenerator = new ColorMatrixGenerator(mouseLocation);
        colorMatrixGenerator.takeFocusedPixelMatrix();
        moveToMouseLocation(0, mouseLocation.y);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignore) {
        }
        colorMatrixGenerator.takeUnfocusedPixelMatrix();
        moveToMouseLocation();
    }
}
