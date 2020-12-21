package raidClicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static java.awt.MouseInfo.getPointerInfo;

public final class MouseActions {

    private final Robot robot;
    private Point mouseLocation;

    public MouseActions() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("No robot for you!");
        }
    }

    public void setLocation(Point mouseLocationFromUi) {
        mouseLocation = mouseLocationFromUi;
    }

    public Point getMouseLocation() {
        return mouseLocation;
    }

    public MouseActions takeMouseLocationFromScreen() {
        mouseLocation = getPointerInfo().getLocation();
        return this;
    }

    public MouseActions doubleClick() {
        return doubleClick(25);
    }

    public MouseActions doubleClick(int milliToClick) {
        moveToMouseLocation();
        click();
        Timer timer = new Timer(milliToClick, null);
        timer.addActionListener(e -> {
            click();
            timer.stop();
        });
        timer.start();

        return this;
    }

    private void click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void moveToMouseLocation() {
        robot.mouseMove(mouseLocation.x, mouseLocation.y);
    }
}
